package Ebonoyu.Chat;

//ChatClient.java
//チャットクライアントサンプルプログラム
//チャットサーバーが動いているコンピュータに対して
//接続を行う。
//コマンドラインパラメータとして接続先アドレスとポート番号を
//指定する。
import java.net.*;
import java.io.*;

class ChatClient 
{
  public static void main(String[] args)
  {
    try {
      InetSocketAddress socketAddress = 
      new InetSocketAddress(args[0], Integer.parseInt(args[1]));
      Socket socket = new Socket();
      socket.connect(socketAddress, 10000);

      InetAddress inetadrs;
      if ((inetadrs = socket.getInetAddress()) != null) {
        System.out.println("接続先: " + inetadrs);
      }
      else {
        System.out.println("接続失敗");
        //return;
      }

      //送信するメッセージは以下の行に記述されているもの。
      String message = "チャット接続送信テスト。";
      PrintWriter writer = new PrintWriter(socket.getOutputStream());
      System.out.println("送信メッセージ: " + message);
      writer.println(message);

      writer.close();
      socket.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  } 
}
