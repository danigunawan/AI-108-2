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
