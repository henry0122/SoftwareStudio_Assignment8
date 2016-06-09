# SoftwareStudio_Assignment8

## Client
  利用lab10的code來修改，在ip_page的地方當我按下button我便開啟一個thread來連線server。
  當到了MainLayout的部分也就是簡易計算機功能的地方幾乎都跟lab10一樣，只在onClick的地方
  多加了sendMessage()來傳送結果給server。

## Server
  用以前lab的code來修改，開了一個包含main的MyWindow這個class呼叫Server這個class裡的
  runforever來等待client的連線，而在連到後開啟thread並在裡頭用reader讀取client傳來的
  結果，最後在顯示在視窗裡。
