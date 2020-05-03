# Colab-Pandas 基本
引用Pandas：  
import pandas as pd  
pd.__version__  
# series 運作
pd.Series(['San Francisco', 'San Jose', 'Sacramento'])
# series  + DataFrame
city_names = pd.Series(['San Francisco', 'San Jose', 'Sacramento'])  
population = pd.Series([852469, 1015785, 485199])  
pd.DataFrame({ 'City name': city_names, 'Population': population })  
