
 
from keras.datasets import mnist  
import numpy as np  
from keras.models import Sequential  
from keras.layers import Dense  
from keras.layers import Dropout  
from keras.layers import Flatten  
from keras.layers.convolutional import  Conv2D  
from keras.layers.convolutional import MaxPooling2D  
from keras.utils import np_utils  
from keras import backend  
backend.set_image_data_format('channels_first')  


# 設定隨機種子  
seed = 7  
np.random.seed(seed)  

# 從Keras導入Mnist資料集  
(X_train, y_train), (X_validation, y_validation) = mnist.load_data()  
  
X_train = X_train.reshape(X_train.shape[0], 1, 28, 28).astype('float32')  
X_validation = X_validation.reshape(X_validation.shape[0], 1, 28, 28).astype('float32')  

# 格式化資料到0-1之前  
X_train = X_train / 255  
X_validation = X_validation / 255  

# one-hot編碼  
y_train = np_utils.to_categorical(y_train)  
y_validation = np_utils.to_categorical(y_validation)  
  
# 創建模型  
def create_model():  
    model = Sequential()  
    model.add(Conv2D(30, (5, 5), input_shape=(1, 28, 28), activation='relu'))  
    model.add(MaxPooling2D(pool_size=(2, 2)))  
    model.add(Conv2D(15, (3, 3), activation='relu'))  
    model.add(MaxPooling2D(pool_size=(2, 2)))  
    model.add(Dropout(0.2))  
    model.add(Flatten())  
    model.add(Dense(units=128, activation='relu'))  
    model.add(Dense(units=50, activation='relu'))  
    model.add(Dense(units=10, activation='softmax'))  

    # 編譯模型  
    model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])  
    return model  

model = create_model()  

model.summary()  

model.fit(X_train, y_train, epochs=10, batch_size=200, verbose=2)  

score = model.evaluate(X_validation, y_validation, verbose=0)  
print('CNN_Large: %.2f%%' % (score[1] * 100))  
#  怪怪的
![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/%E9%8C%AF%E8%AA%A4.JPG) 

#  在MNIST数据集上训练一个简单的convnet。

from __future__ import print_function
import keras
from keras.datasets import mnist
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten
from keras.layers import Conv2D, MaxPooling2D
from keras import backend as K

batch_size = 128
num_classes = 10
epochs = 12

# 輸入圖像尺寸
img_rows, img_cols = 28, 28

# 數據，分為訓練集和測試集
(x_train, y_train), (x_test, y_test) = mnist.load_data()  

if K.image_data_format() == 'channels_first':  
    x_train = x_train.reshape(x_train.shape[0], 1, img_rows, img_cols)  
    x_test = x_test.reshape(x_test.shape[0], 1, img_rows, img_cols)  
    input_shape = (1, img_rows, img_cols)  
else:  
    x_train = x_train.reshape(x_train.shape[0], img_rows, img_cols, 1)  
    x_test = x_test.reshape(x_test.shape[0], img_rows, img_cols, 1)  
    input_shape = (img_rows, img_cols, 1)  

x_train = x_train.astype('float32')  
x_test = x_test.astype('float32')  
x_train /= 255   
x_test /= 255  
print('x_train shape:', x_train.shape)   
print(x_train.shape[0], 'train samples')  
print(x_test.shape[0], 'test samples')  

# 將類向量轉換為二進制類矩陣
y_train = keras.utils.to_categorical(y_train, num_classes)  
y_test = keras.utils.to_categorical(y_test, num_classes)  

model = Sequential()  
model.add(Conv2D(32, kernel_size=(3, 3),  
                 activation='relu',  
                 input_shape=input_shape))  
model.add(Conv2D(64, (3, 3), activation='relu'))  
model.add(MaxPooling2D(pool_size=(2, 2)))  
model.add(Dropout(0.25))  
model.add(Flatten())  
model.add(Dense(128, activation='relu'))  
model.add(Dropout(0.5))  
model.add(Dense(num_classes, activation='softmax'))  

model.compile(loss=keras.losses.categorical_crossentropy,  
              optimizer=keras.optimizers.Adadelta(),  
              metrics=['accuracy'])  

model.fit(x_train, y_train,  
          batch_size=batch_size,  
          epochs=epochs,   
          verbose=1,  
          validation_data=(x_test, y_test))  
score = model.evaluate(x_test, y_test , verbose=0)  
print('Test loss:', score[0])  
print('Test accuracy:', score[1])  
![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/C1.JPG)   

