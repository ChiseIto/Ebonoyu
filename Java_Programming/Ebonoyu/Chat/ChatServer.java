package Ebonoyu.Chat;

//ChatServer.java
//チャットサーバープログラム
//チャットクライアントプログラムからの接続を待つ。
//接続後は1行の文字列読み取りを行い、接続終了する。

import java.net.*;
import java.io.*;

class ChatServer 
{
  public static void main(String[] args)
  {
    try {
      // サーバーソケット作成
      //起動時パラメータからポートを読み取り、
      //そのポートで接続要求を待つ
      //ServerSocketクラスはクライアントからの接続を待ち、
      //srvSock.accept();によって接続したSocketオブジェクト
      //を返す。
      //その後の通信には、このSocketオブジェクトを使用する。
      int port = Integer.parseInt(args[0]);
      ServerSocket srvSock = new ServerSocket(port);
  
      // 接続待機。接続完了後、次行命令に移る。
      Socket socket = srvSock.accept();
      //接続先アドレスを表示
      System.out.println("接続先: " + socket.getInetAddress());

      //通信処理 
      //ソケットの入力ストリームから文字列を1行読み取る。
      BufferedReader reader = new BufferedReader
        (new InputStreamReader(socket.getInputStream()));
      String line = reader.readLine();
      //読み取った文字列を表示
      System.out.println("受信メッセージ:" + line);

      //終了処理　このプログラムは1行読み取ったら終了する。
      //通信を続けるのであれば、reader.readLine();を
      //ループするが、終了コマンドをチェックする等の処理を
      //記述する。
      reader.close();
      socket.close();
      srvSock.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  } 
}