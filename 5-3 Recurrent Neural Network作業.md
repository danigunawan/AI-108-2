# 5-3 Recurrent Neural Network作業

## //----SimpleRNN------------//  
from keras.layers import SimpleRNN,LSTM,Dropout,Dense  
import numpy as np  
from keras.utils import np_utils  
np.random.seed(10)  
from keras.datasets import mnist  
import matplotlib.pyplot as plt  
from keras.models import *  
#from keras.models import Sequential  
#建立訓練資料和測試資料，包括訓練特徵集、訓練標籤和測試特徵集、測試標籤	
(train_feature, train_label),\
(test_feature, test_label) = mnist.load_data()  
#將 Features 特徵值換為 60000*28*28 的 3 維矩陣  
train_feature_vector = train_feature.reshape(len(train_feature),28,28).astype('float32')  
test_feature_vector = test_feature.reshape(len(test_feature),28,28).astype('float32')  
#Features 特徵值標準化  
train_feature_normalize = train_feature_vector/255  
test_feature_normalize = test_feature_vector/255  

#label 轉換為 One-Hot Encoding 編碼  
train_label_onehot = np_utils.to_categorical(train_label)  
test_label_onehot = np_utils.to_categorical(test_label)  
model = Sequential()  
model.add(SimpleRNN(  
    # input_shape=(TIME_STEPS, INPUT_SIZE)  
    # TIME_STEPS 讀取次數，INPUT_SIZE 每次讀取多少個像素  
    input_shape=(28, 28),   
    units=256, # 隱藏層：256 個神經元  
    unroll=True, #計算時展開結構  
))  
model.add(Dropout(0.1))  
model.add(Dense(units=10, kernel_initializer='normal', activation='softmax'))  
model.summary()  
model.compile(loss='categorical_crossentropy',  
              optimizer='adam', metrics=['accuracy'])  

train_history =model.fit(x=train_feature_normalize,  
                         y=train_label_onehot,validation_split=0.2, 
                         epochs=10, batch_size=200,verbose=2)  

scores = model.evaluate(test_feature_normalize, test_label_onehot)  
print('\n準確率=',scores[1])  

prediction=model.predict_classes(test_feature_normalize)  
prediction  

![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/RA.JPG) 

## //----使用LSTM------------// 
from keras.layers import SimpleRNN,LSTM,Dropout,Dense
import numpy as np
from keras.utils import np_utils
np.random.seed(10)
from keras.datasets import mnist
import matplotlib.pyplot as plt
from keras.models import *
from keras.models import Sequential
## 建立訓練資料和測試資料，包括訓練特徵集、訓練標籤和測試特徵集、測試標籤	
(train_feature, train_label),\
(test_feature, test_label) = mnist.load_data()  

## 將 Features 特徵值換為 60000*28*28 的 3 維矩陣
train_feature_vector = train_feature.reshape(len(train_feature),28,28).astype('float32')
test_feature_vector = test_feature.reshape(len(test_feature),28,28).astype('float32')
## Features 特徵值標準化
train_feature_normalize = train_feature_vector/255
test_feature_normalize = test_feature_vector/255
## label 轉換為 One-Hot Encoding 編碼
train_label_onehot = np_utils.to_categorical(train_label)
test_label_onehot = np_utils.to_categorical(test_label)
model = Sequential()
model.add(LSTM(
    input_shape=( 28, 28),
    units=256,
    unroll=True
))
model.add(Dropout(0.1))
model.add(Dense(units=10, kernel_initializer='normal', activation='softmax'))
model.summary()
model.compile(loss='categorical_crossentropy', 
              optimizer='adam', metrics=['accuracy'])
train_history =model.fit(x=train_feature_normalize,
                         y=train_label_onehot,validation_split=0.2, 
                         epochs=10, batch_size=200,verbose=2)
scores = model.evaluate(test_feature_normalize, test_label_onehot)
print('\n準確率=',scores[1])
prediction=model.predict_classes(test_feature_normalize)
prediction

















## 带有Keras的递归神经网络（RNN）
##  设定  
import collections  
import matplotlib.pyplot as plt  
import numpy as np  
import tensorflow as tf  
from tensorflow.keras import layers     
##  建立一个简单的模型  
model = tf.keras.Sequential()   
##  添加一個Embingding層，期望輸入vocab的大小為1000，並且    
###  輸出嵌入尺寸為64。   
model.add(layers.Embedding(input_dim=1000, output_dim=64))   
###  添加具有128個內部單元的LSTM層。  
model.add(layers.LSTM(128))    
## 添加具有10個單位的密集層。  
model.add(layers.Dense(10))   
model.summary()   
## 输出和状态   
model = tf.keras.Sequential()  
model.add(layers.Embedding(input_dim=1000, output_dim=64))    
## GRU的輸出將是形狀的3D張量（batch_size，timesteps，256）  
model.add(layers.GRU(256, return_sequences=True))    
## SimpleRNN的輸出將是形狀的2D張量（batch_size，128）    
model.add(layers.SimpleRNN(128))    
model.add(layers.Dense(10))    
model.summary()   
## //------------------------//  
encoder_vocab = 1000   
decoder_vocab = 2000  
encoder_input = layers.Input(shape=(None, ))  
encoder_embedded = layers.Embedding(input_dim=encoder_vocab, output_dim=64)(encoder_input)  
## 返回狀態除了輸出  
output, state_h, state_c = layers.LSTM(  
    64, return_state=True, name='encoder')(encoder_embedded)  
encoder_state = [state_h, state_c]  
decoder_input = layers.Input(shape=(None, ))  
decoder_embedded = layers.Embedding(input_dim=decoder_vocab, output_dim=64)(decoder_input)  

![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/RNN-1.JPG) 

## 將這兩種狀態作為初始狀態傳遞到新的LSTM層  
decoder_output = layers.LSTM(   
    64, name='decoder')(decoder_embedded, initial_state=encoder_state)   
output = layers.Dense(10)(decoder_output)   
model = tf.keras.Model([encoder_input, decoder_input], output)    
model.summary()   

![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/RNN-2.JPG) 

