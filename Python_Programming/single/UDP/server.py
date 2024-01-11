# server.py
import socket

ip_address = '127.0.0.1'
port = 7010
buffer_size = 4092

# Socketの作成
with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
    # IP Adress とPort番号をソケット割り当てる
    s.bind((ip_address, port))
    # while Trueでクライアントからの要求を待つ
    while True:
        # データを受信する
        data, addr = s.recvfrom(buffer_size)
        print('data-> {}, addr->{}'.format(data, addr))