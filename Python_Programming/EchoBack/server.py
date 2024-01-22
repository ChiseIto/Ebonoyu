<<<<<<< HEAD
import socket

# ソケットオブジェクトの作成
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# IPとポートの設定
server_socket.bind(('localhost', 9999))

# 接続待機
server_socket.listen(1)

print('サーバーが起動しました。クライアントの接続を待っています...')

# クライアントからの接続を受け入れる
client_socket, address = server_socket.accept()

print(f"{address} から接続がありました。")

while True:
    # メッセージの受信
    msg = client_socket.recv(1024).decode('utf-8')
    if msg == 'exit':
        break
    print(f"クライアント: {msg}")

    # メッセージの送信
    msg = input('サーバー: ')
    client_socket.send(msg.encode('utf-8'))

# ソケットのクローズ
client_socket.close()
=======
import socket

# ソケットオブジェクトの作成
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# IPとポートの設定
server_socket.bind(('localhost', 9999))

# 接続待機
server_socket.listen(1)

print('サーバーが起動しました。クライアントの接続を待っています...')

# クライアントからの接続を受け入れる
client_socket, address = server_socket.accept()

print(f"{address} から接続がありました。")

while True:
    # メッセージの受信
    msg = client_socket.recv(1024).decode('utf-8')
    if msg == 'exit':
        break
    print(f"クライアント: {msg}")

    # メッセージの送信
    msg = input('サーバー: ')
    client_socket.send(msg.encode('utf-8'))

# ソケットのクローズ
client_socket.close()
>>>>>>> b245942559d3dafc59083d7dce5fa6bc883ba3aa
server_socket.close()