#  5-1 keras建置神經網路
作業
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
![image](https://github.com/SuWeizhe1124/3-19/blob/master/HJJPG.JPG)   
#測試 Y = 2 * X + 2 + np.random.normal(0, 0.05, (200, ))    
![image](https://github.com/SuWeizhe1124/3-19/blob/master/HJJPG.JPG)   
#測試 Y = 2 * X + 2 + np.random.normal(0, 0.05, (200, ))    
![image](https://github.com/SuWeizhe1124/3-19/blob/Kers%20%E6%B8%AC%E8%A9%A6/A2.JPG)   
