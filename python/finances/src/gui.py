# Libraries
from tkinter import *
from tkcalendar import DateEntry
import os
# Config file
from config import *
# Custom classes
from data import *


def init():
    main = __create_main_win()
    main.mainloop()
    
def __create_main_win():
    main = Tk()
    main.title('Finance Manager')
    __geometry(main, 7, 3)

    # Buttons for the GUI
    btn_auto = Button(main, text='Automatic Entry', width=15, command=lambda: __create_auto_win(main))
    btn_auto.grid(row=1, column=1)

    btn_manual = Button(main, text='Manual Entry', width=15, command=lambda: __create_manual_win(main))
    btn_manual.grid(row=2, column=1)

    btn_fix = Button(main, text='Fix Data', width=15, command=lambda: __create_fix_win(main))
    btn_fix.grid(row=3, column=1)

    btn_close = Button(main, text='Close', width=15, command=main.destroy)
    btn_close.grid(row=5, column=1)

    return main

def __create_auto_win(parent: Tk):
    """Create the automatic entry window."""
    auto = Toplevel(parent)                 # create a new window
    auto.title('Automatic Entry')           # set the title of the window

    # Setup grid layout
    __geometry(auto, 6, 4)                  # set the geometry and grid layout

    # Dropdown menu for selecting account
    card_var = StringVar(auto)
    card_var.set('Select Account')          # Default value
    card_menu = OptionMenu(auto, card_var, *CARDS)
    card_menu.grid(row=1, column=1, columnspan=2)

    # Button to import CSV file
    btn_import = Button(auto, text='Import CSV', width=15, command=lambda: import_csv(auto))
    btn_import.grid(row=2, column=1, columnspan=2)

    # Back and Submit buttons
    btn_back = Button(auto, text='Back', width=15, command=auto.destroy)
    btn_back.grid(row=4, column=1)

    btn_submit = Button(auto, text='Submit', width=15)
    btn_submit.grid(row=4, column=2)

def __create_manual_win(parent: Tk):
    """Create the manual entry window."""
    def save_manual_entry():
            date = entry_date.get()
            descr = entry_descr.get()
            amount = entry_amnt.get()
            cat = menu_cat.get()
            acct = menu_acct.get()
            faye = check_faye.get()
            oliver = check_oliver.get()
            monthly = check_monthly.get()
            annual = check_annual.get()

            if date and descr and amount and cat != 'Select Category' and acct != "Select Account" and (faye or oliver):
                append_data(str.lower(acct), (date, descr, amount, cat, acct))
                # Show success message
                label_msg.config(text="Data saved successfully")
                # Clear the input fields except bank and date
                entry_descr.delete(0, END)
                entry_amnt.delete(0, END)
                menu_cat.delete(0, END) 
                # Clear any previous error message
                label_msg.config(text="") 
            else:
                label_msg.config(text="Required field(s) missing")

    manual = Toplevel(parent)                 # create a new window
    manual.title('Manual Entry')            # set the title of the window

    # Setup grid layout
    __geometry(manual, 9, 5)         # set the geometry and grid layout

    # Date entry
    label_date = Label(manual, text='Date*', width=15, anchor='w') 
    label_date.grid(row=1, column=1)
    # date picker
    entry_date = DateEntry(manual, width=15, background='darkgreen', foreground='lightgray', borderwidth=2)
    entry_date.grid(row=1, column=2)

    # Description entry
    label_descr = Label(manual, text='Description*', width=15, anchor='w')
    label_descr.grid(row=2, column=1)
    entry_descr = Entry(manual,width=15)
    entry_descr.grid(row=2, column=2)

    # Amount entry
    label_amnt = Label(manual, text='Amount*', width=15, anchor='w')
    label_amnt.grid(row=3, column=1)
    entry_amnt = Entry(manual,width=15)
    entry_amnt.grid(row=3, column=2)

    # Category entry
    label_cat = Label(manual, text='Category*', width=15, anchor='w')
    label_cat.grid(row=4, column=1)
    # dropdown menu for category
    var_cat = StringVar(manual)
    var_cat.set('Select Category')          # Default value
    menu_cat = OptionMenu(manual, var_cat, 'Food', 'Rent', 'Utilities', 'Entertainment', 'Other')
    menu_cat.grid(row=4, column=2)

    # Account entry
    label_acct = Label(manual, text='Account*', width=15, anchor='w')
    label_acct.grid(row=5, column=1)
    # dropdown menu for account
    var_acct = StringVar(manual)
    var_acct.set('Select Account')          # Default value
    menu_acct = OptionMenu(manual, var_acct, *CARDS)
    menu_acct.grid(row=5, column=2)

    # Faye and Oliver checkboxes
    var_faye = IntVar()
    var_oliver = IntVar()
    check_faye = Checkbutton(manual, text='Faye*', width=10, anchor='w', variable=var_faye)
    check_faye.grid(row=1, column=3)
    check_oliver = Checkbutton(manual, text='Oliver*', width=10, anchor='w', variable=var_oliver)
    check_oliver.grid(row=2, column=3)

    # Spacer frames
    spacer_check = Frame(manual, width=5)
    spacer_check.grid(row=0, column=4)

    # Monthly and Annual checkboxes
    var_monthly = IntVar()
    var_annual = IntVar()
    check_monthly = Checkbutton(manual, text='Monthly', width=10, anchor='w', variable=var_monthly)
    check_monthly.grid(row=3, column=3)
    check_annual = Checkbutton(manual, text='Annual', width=10, anchor='w', variable=var_annual)
    check_annual.grid(row=4, column=3)

    # Message label
    label_msg = Label(manual, text='',anchor='center', foreground='red')
    label_msg.grid(row=6, column=1, columnspan=3)

    # Back and Submit buttons
    btn_back = Button(manual, text='Back', width=10, command=manual.destroy)
    btn_back.grid(row=7, column=1, sticky='e')

    btn_submit = Button(manual, text='Submit', width=10)
    btn_submit.grid(row=7, column=3, columnspan=2, sticky='w')

    return manual

def __create_fix_win(parent: Tk):
    """Create the fix data window."""
    
    fix = __create_manual_win(parent)


def __geometry(window: Tk, rows: int, cols: int) -> Tk:
    """Set the geometry and grid layout for a window.

    Args:
        window (Tk): The Tkinter window to configure.
        size (str): The size of the window (e.g., '800x600').
        rows (int): The number of rows in the grid layout.
        cols (int): The number of columns in the grid layout.

    Returns:
        Tk: The configured Tkinter window.
    """
    # window.geometry(size)

    for i in range(rows):
        window.grid_rowconfigure(i, weight=0, minsize=32)

    for i in range(cols):
        window.grid_columnconfigure(i, weight=0, minsize=32)
    return window

if __name__ == '__main__':
    init()