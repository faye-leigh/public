from tkinter import *
from csv import *
'''
0. gui options (open html, import, cancel)
1. import csv(s)
2. trim data
3. add category
4. get user input per data for who and category
5. export/append to data csv file(s)
6. import data and do calcs
7. write to html
8. open html
9. -> 0
'''

IMPORT_FILE_NAME = ".import/test.CSV"
DATA_FILE_NAME = ".data/data.csv"
FIELDS = ['Date', 'Description', 'Amount']

def import_csv():
    return

import_file = open(IMPORT_FILE_NAME, 'r')
csv_reader = reader(import_file)
data_file = open(DATA_FILE_NAME, 'a')
csv_writer = writer(data_file)
data = []

for row in csv_reader:
    row.pop(0)
    row.pop(3)
    row.pop(3)
    data.append(row)
data.pop(0)
data.sort(reverse=False)

# csv_writer.writerow(FIELDS)
csv_writer.writerows(data)


