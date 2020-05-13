#  5-1 keras建置神經網路  
# 作業  
使用keras建置神經網路求解簡單線性回歸問題  
import numpy as np  
#測試 Y = 2 * X + 2 + np.random.normal(0, 0.05, (200, ))   
#測試 Y = 0.2 * X + 2 + np.random.normal(0, 0.05, (200, ))    
np.random.seed(1337)  # for reproducibility  
from keras.models import Sequential  
from keras.layers import Dense  
import matplotlib.pyplot as plt   
# create some data  
X = np.linspace(-1, 1, 200)  
np.random.shuffle(X)    # randomize the data  
Y = 0.5 * X + 2 + np.random.normal(0, 0.05, (200, ))  
# plot data  
plt.scatter(X, Y)  
plt.show()    
//----------------------------------------------//  
#測試 Y = 2 * X + 2 + np.random.normal(0, 0.05, (200, ))   
![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/A1.png)     
#測試 Y = 0.2 * X + 2 + np.random.normal(0, 0.05, (200, ))   
![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/A2.jpg)     
#測試 Y = 0.5 * X + 2 + np.random.normal(0, 0.05, (200, ))    
![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/A3..jpg)     
//----------------------------------------------//  

X_train, Y_train = X[:160], Y[:160]     # train 前 160 data points  
X_test, Y_test = X[160:], Y[160:]   
model = Sequential()  
model.add(Dense(output_dim=1, input_dim=1))  
# choose loss function and optimizing method  
model.compile(loss='mse', optimizer='sgd')  
# training  
print('Training -----------')  
for step in range(301):  
cost = model.train_on_batch(X_train, Y_train)  
if step % 100 == 0:  
print('train cost: ', cost)   
# test  
print('\nTesting ------------')  
cost = model.evaluate(X_test, Y_test, batch_size=40)  
print('test cost:', cost)  
W, b = model.layers[0].get_weights()  
print('Weights=', W, '\nbiases=', b)  
# plotting the prediction  
Y_pred = model.predict(X_test)  
plt.scatter(X_test, Y_test)  
plt.plot(X_test, Y_pred)  
plt.show()  
![image](https://github.com/SuWeizhe1124/3-19/blob/master/Kers%20%E6%B8%AC%E8%A9%A6/k.JPG)   

