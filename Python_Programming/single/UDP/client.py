# client.py
import socket

ip_address = '127.0.0.1'
port = 7010
buffer_size = 4092

# Socketの作成
with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
    # データを送信する
    s.sendto(b'I sent a message.', (ip_address, port))