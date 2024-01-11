import socket

# ソケットオブジェクトの作成
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# サーバーに接続
client_socket.connect(('localhost', 9999))

while True:
    # メッセージの送信
    msg = input('クライアント: ')
    client_socket.send(msg.encode('utf-8'))

    # メッセージの受信
    msg = client_socket.recv(1024).decode('utf-8')
    print(f"サーバー: {msg}")

    if msg == 'exit':
        break

# ソケットのクローズ
client_socket.close()