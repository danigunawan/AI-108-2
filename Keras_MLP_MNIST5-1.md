# 5-1
Keras_MLP_MNIST 作業  
# Keras Sequential 開發模式:載入資料集
from tensorflow.python.keras.datasets import mnist  
(x_train, y_train), (x_test, y_test) = mnist.load_data()  
print('x_train.shape:', x_train.shape)  
print('x_test.shape:', x_test.shape)  
print('y_train.shape:', y_train.shape)  
print('y_test.shape:', y_test.shape)  
x_train = x_train.reshape(60000, 784)   
x_test = x_test.reshape(10000, 784)  
x_train = x_train/255.  
x_test = x_test/255  
歸一化  
print('x_train.shape:', x_train.shape)  
print('x_test.shape:', x_test.shape)  
print('y_train.shape:', y_train.shape)  
print('y_test.shape:', y_test.shape)  
#  執行後  
![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/G2.JPG)  
#  定義(建置)MLP神經網路  
from tensorflow.python.keras.models import Sequential  
from tensorflow.python.keras.layers import Dense  
model = Sequential()  
#增加 Hidden Layer  
#有64個神經元  
#測試報告:改看看128, 256,1000)  
model.add(  
    Dense(  
        units=64,   
        input_shape=(784,),  
        activation='relu'  
    )  
)  
#  執行後  
![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/G1.JPG)  

# 設定模型的執行項目
model.compile(  
    optimizer='adam',  
    loss='categorical_crossentropy',  
    metrics=['accuracy']  
)

# 訓練
history_adam = model.fit(  
    x_train,  
    y_train,  
    batch_size=32,  
    epochs=20,  
    validation_split=0.2,  
)
 
