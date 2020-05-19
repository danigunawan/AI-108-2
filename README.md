# 深度學習工程:
### 數位影像處理  : Harr 小波 程式碼 
### 參考  :http://www1.pu.edu.tw/~ychu/IP1062/
### GOOGLE  :https://colab.research.google.com/notebooks/intro.ipynb
## 個人看法: 由於數字辨識屬於獨立事件，相同物體個人認為後面做權重正規沒有用，因為本身就正規了，如果是水果應該就有用不同的事件以及物體，數字的話前面的通常會用不同的濾波去使用，為了使正規的物體產生變化，使後面更容易辨識。
50分
## 辨識率 98% :   https://machinelearningmastery.com/handwritten-digit-recognition-using-convolutional-neural-networks-python-keras/
根據上述所說:
注意：根據學習算法的隨機性質，您的特定結果可能會有所不同。考慮運行該示例幾次。
在GPU（例如，AWS）上運行時間可能會花費大約45秒。您可以看到網絡實現了0.95％的錯誤率，這比上面的簡單多層感知器模型要好。
## size 3-3 99% 依照這邏輯一開始3*3就好了  多5*5 是怎  你敢信?
60分
## 辨識率 99% 5-5 但穩定度不高 不好 地39步才99 必須更謹慎一點 :   https://medium.com/jameslearningnote/%E8%B3%87%E6%96%99%E5%88%86%E6%9E%90-%E6%A9%9F%E5%99%A8%E5%AD%B8%E7%BF%92-%E7%AC%AC5-1%E8%AC%9B-%E5%8D%B7%E7%A9%8D%E7%A5%9E%E7%B6%93%E7%B6%B2%E7%B5%A1%E4%BB%8B%E7%B4%B9-convolutional-neural-network-4f8249d65d4f
70分
##  最佳解 :https://kknews.cc/zh-tw/news/g9jxx4m.html   穩定度最佳第10步 辨識率 99 size 4*4
## 程式碼 3*3 

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

# input image dimensions
img_rows, img_cols = 28, 28

# the data, split between train and test sets
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

# convert class vectors to binary class matrices
y_train = keras.utils.to_categorical(y_train, num_classes)
y_test = keras.utils.to_categorical(y_test, num_classes)

model = Sequential()
model.add(Conv2D(32, kernel_size=(3, 3),
                 activation=LeakyReLU(),
                 input_shape=input_shape))
model.add(Conv2D(64, (3, 3), activation=LeakyReLU()))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Dropout(0.25))
model.add(Flatten())
model.add(Dense(128, activation=LeakyReLU()))
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
score = model.evaluate(x_test, y_test, verbose=0)


print('Test loss:', score[0])
print('Test accuracy:', score[1])
