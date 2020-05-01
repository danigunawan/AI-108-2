# 5-1
# Keras 課作業 解決XOR  
from keras.models import Sequential  
from keras.layers.core import Dense, Dropout, Activation  
from keras.optimizers import SGD  
import numpy as np   

X = np.array([[0,0],[0,1],[1,0],[1,1]])  
y = np.array([[0],[1],[1],[0]])  

model = Sequential()  
model.add(Dense(8, input_dim=2))  
model.add(Activation('tanh'))  
model.add(Dense(1))  
model.add(Activation('sigmoid'))  
sgd = SGD(lr=0.1)  
model.summary()  
model.compile(loss='binary_crossentropy', optimizer=sgd)  
model.fit(X, y, batch_size=1, nb_epoch=1000)  
#model.fit(X, y, show_accuracy=True, batch_size=1, nb_epoch=1000)  
print(model.predict_proba(X))  
# 執行後 畫面
............................
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 981/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 982/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 983/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 984/1000
4/4 [==============================] - 0s 3ms/step - loss: 0.0047
Epoch 985/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 986/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 987/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 988/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 989/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 990/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 991/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 992/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 993/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 994/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0047
Epoch 995/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0046
Epoch 996/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0046
Epoch 997/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0046
Epoch 998/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0046
Epoch 999/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0046
Epoch 1000/1000
4/4 [==============================] - 0s 2ms/step - loss: 0.0046
[[0.00221375]
 [0.9948657 ]
 [0.9953145 ]
 [0.00631008]]
