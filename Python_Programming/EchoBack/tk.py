import tkinter as tk 

# ウィンドウ作成
root = tk.Tk()
# タイトルの文字
root.title("ゑぼの湯")
# 横x縦
root.geometry("1200x800")

# 配置したいテキスト
label = tk.Label(root, text="こんにちは")
# packメソッドで配置
label.pack()

# テキストボックスのサイズを指定
txtBox = tk.Entry()
txtBox.configure(state='normal', width=50)
# packメソッドで配置
txtBox.pack()

# テキストボックスに入力する文字
def outputWords(event):
    txtBox.insert(tk.END, 'Hello!!!')
# テキストボックスを配置
txtBox = tk.Entry()
txtBox.configure(state='normal', width=50)
txtBox.pack()
# 座標を指定して配置
button = tk.Button(text='ボタン', width=30)
button.place(x=90, y=120)
# ボタンが押された場合
button.bind('<Button-1>', outputWords)

# キャンバス作成
canvas = tk.Canvas(root, bg="#deb887", height=200, width=200)
# キャンバス表示
canvas.place(x=0, y=0)

# ウィンドウ表示に必須
root.mainloop()
