# 5-3 Recurrent Neural Network作業
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

![image](https://github.com/SuWeizhe1124/3-19/blob/master/1234.JPG) 

## 將這兩種狀態作為初始狀態傳遞到新的LSTM層  
decoder_output = layers.LSTM(   
    64, name='decoder')(decoder_embedded, initial_state=encoder_state)   
output = layers.Dense(10)(decoder_output)   
model = tf.keras.Model([encoder_input, decoder_input], output)    
model.summary()   

![image](https://github.com/SuWeizhe1124/3-19/blob/master/1234.JPG) 

