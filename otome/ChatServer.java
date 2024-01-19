import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * チャットサーバ
 */
public class ChatServer {
    // クライアントsocketリスト
    public static List<Socket> clientSockets = new ArrayList<Socket>();

    public ChatServer() throws IOException {
        // サーバソケット生成
        @SuppressWarnings("resource")
        ServerSocket ss = new ServerSocket(30000);
        while (true) {
            // クライアント受け入れ
            Socket socket = ss.accept();
            // クライアントリストへ追加
            clientSockets.add(socket);
            // 別スレッド起動
            new ServerThread(socket).start();
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatServer();
    }
}

/**
 *  スレッド処理
 * @author liguofeng
 */
class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // inputstream
            InputStream in = socket.getInputStream();
            // outputstream
            OutputStream out = socket.getOutputStream();
            // バッファーサイズ
            byte[] buff = new byte[1024];
            String req = "";
            // データ受信
            int count = in.read(buff);
            if (count > 0) {
                // 読み取り
                req = new String(buff, 0, count);
                System.out.println("req : " + req);
                // socketkey
                String secKey = getSecWebSocketKey(req);
                System.out.println("secKey : " + secKey);
                String response = "HTTP/1.1 101 Switching Protocols\r\nUpgrade: "
                        + "websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept: "
                        + getSecWebSocketAccept(secKey) + "\r\n\r\n";
                System.out.println("secAccept : "
                        + getSecWebSocketAccept(secKey));
                out.write(response.getBytes());
            }
            int hasRead = 0;

            // 継続的に読み取り
            while ((hasRead = in.read(buff)) > 0) {
                /*
                * WebSocketプロトコール 3～6は隠しコード 7バイト目からがデータ 3～6で後ろのデータを処理
                */
                for (int i = 0; i < hasRead - 6; i++) {
                    buff[i + 6] = (byte) (buff[i % 4 + 2] ^ buff[i + 6]);
                }
                // データ読み取り
                String pushMsg = new String(buff, 6, hasRead - 6, "UTF-8");
                // すべてのクライアントにデータ送信
                for (Iterator<Socket> it = ChatServer.clientSockets.iterator(); it
                        .hasNext();) {
                    try {
                        Socket s = it.next();
                        //  送信する際に、2バイトは必ず受信と同じである必要がある
                        byte[] pushHead = new byte[2];
                        pushHead[0] = buff[0];
                        // 長さ
                        pushHead[1] = (byte) pushMsg.getBytes("UTF-8").length;
                        // ヘッダー出力
                        s.getOutputStream().write(pushHead);
                        // データ出力
                        s.getOutputStream().write(pushMsg.getBytes("UTF-8"));
                    } catch (SocketException ex) {
                        // クライアント削除
                        it.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // クローズ
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
    * WebSocketのキー取得
    *
    * @param req
    * @return
    */
    private String getSecWebSocketKey(String req) {
        Pattern p = Pattern.compile("^(Sec-WebSocket-Key:).+",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher(req);
        if (m.find()) {
            // Sec-WebSocket-Key取得
            String foundstring = m.group();
            return foundstring.split(":")[1].trim();
        } else {
            return null;
        }
    }

    /**
    * WebSocketのSecKeyからSecAccept計算
    *
    * @param key
    * @return
    * @throws Exception
    */
    private String getSecWebSocketAccept(String key) throws Exception {

        String guid = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        key += guid;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(key.getBytes("UTF-8"), 0, key.length());
        byte[] sha1Hash = md.digest();
        Encoder encoder = Base64.getEncoder();

        return new String(encoder.encode(sha1Hash), "UTF-8");
    }
}
