# 3-19 作業
//-------------------------------------------------//

## 作業 1-1  
import matplotlib.pyplot as plt  
x  = [1, 2, 3, 4, 5, 6, 7, 8, 9]  
y1 = [2, 2, 5, 3, 1, 3, 5, 3, 1]  
y2 = [2, 4, 6, 4, 2, 4, 6, 4, 2]  
plt.plot(x, y1, label="line L")  
plt.plot(x, y2, label="line H")  
plt.plot()  
plt.xlabel("x axis")  
plt.ylabel("y axis")  
plt.title("Line Graph Example")  
plt.legend()  
plt.show()  
plt.plot(x, y1,'r--',label="line L")  
![image](https://github.com/SuWeizhe1124/3-19/blob/master/a1.JPG)  
//-------------------------------------------------//

## 作業 1-2  
import numpy as np  
import pylab as pl  
x = np.arange(0.0, 2.0*np.pi, 0.01)   
y = np.sin(x)  
#畫圖  
pl.plot(x,y)    
pl.xlabel('x')      
pl.ylabel('y')
pl.title('sin')   
pl.show()  
![image](https://github.com/SuWeizhe1124/3-19/blob/master/1234.JPG)  
//------------------------------------------------------//  
## 作業 1-3  
努力中  
//------------------------------------------------------//
  
## 作業 1-4  
import numpy as np  
import matplotlib.pyplot as plt  
t = np.arange(0.0, 2.0, 0.01)  
s = np.sin(2*np.pi*t)   
plt.plot(t, s)  
plt.title(r'$\alpha_i > \beta_i$', fontsize=20)//表示 數學符號  
plt.text(1, -0.6, r'$\sum_{i=0}^\infty x_i$', fontsize=20)  
plt.text(0.6, 0.6, r'$\mathcal{A}\mathrm{sin}(2 \omega t)$',fontsize=20)  
plt.xlabel('time (s)')  
plt.ylabel('volts (mV)')  
plt.show()  
![image](https://github.com/SuWeizhe1124/3-19/blob/master/a4.JPG)   
