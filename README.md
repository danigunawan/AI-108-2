# log 作業  
作業  
import numpy as np  
import matplotlib.pyplot as plt  
from sklearn.model_selection import train_test_split  
np.random.seed(123)# We will use a simple training set  
X = 2 * np.random.rand(500, 1)  
y = 5 + 3 * X + np.random.randn(500, 1)  
fig = plt.figure(figsize=(8,6))  
plt.scatter(X, y)  
plt.title("Dataset")  
plt.xlabel("First feature")  
plt.ylabel("Second feature")   
plt.show()  
  ![image](https://github.com/SuWeizhe1124/3-19/blob/master/HJJPG.JPG) 
# Split the data into a training and test set  
X_train, X_test, y_train, y_test = train_test_split(X, y)  
print(f'Shape X_train: {X_train.shape}')   
print(f'Shape y_train: {y_train.shape}')  
print(f'Shape X_test: {X_test.shape}')  
print(f'Shape y_test: {y_test.shape}')   
  ![image](https://github.com/SuWeizhe1124/3-19/blob/master/擷取.JPG)   
