from tkinter import *
from tkinter import filedialog, messagebox
from tkcalendar import DateEntry
from csv import reader, writer

# Constants for file paths and CSV fields
FIELDS = ['Date', 'Description', 'Amount']
BANKS = ["Apple", "Chase", "Citi", "Discover", "PayPal", "Synchrony", "Venmo"]

# Function to import CSV file
def import_csv():
    # Open file dialog to select CSV file
    file_path = filedialog.askopenfilename(filetypes=[("CSV files", "*.csv"),("CSV files", "*.CSV")])
    if file_path:
        with open(file_path, 'r') as import_file:
            csv_reader = reader(import_file)
            next(csv_reader)  # Skip the header line
            data = []
            for row in csv_reader:
                # Process the row based on the selected bank
                if bank_var.get() == "Bank A":
                    row.pop(0)
                    row.pop(3)
                    row.pop(3)
                elif bank_var.get() == "Bank B":
                    row.pop(1)
                    row.pop(2)
                data.append(row)
            # Append the processed data to the data file
            with open(DATA_FILE_NAME, 'a') as data_file:
                csv_writer = writer(data_file)
                csv_writer.writerows(data)
            # Show success message
            messagebox.showinfo("Success", "CSV imported successfully")

# Function to manually enter data
def manual_entry():
    def save_manual_entry():
        date = entry_date.get()
        description = entry_description.get()
        amount = entry_amount.get()
        bank = bank_var_manual.get()
        if date and description and amount and bank != "Select Bank":
            with open(DATA_FILE_NAME, 'a') as data_file:
                csv_writer = writer(data_file)
                csv_writer.writerow([date, description, amount, bank])
            messagebox.showinfo("Success", "Data saved successfully")
            manual_entry_window.destroy()
        else:
            messagebox.showwarning("Warning", "All fields are required")

    manual_entry_window = Toplevel(root)
    manual_entry_window.title("Manual Data Entry")

    Label(manual_entry_window, text="Date").grid(row=0, column=0, padx=10, pady=5)
    entry_date = DateEntry(manual_entry_window, date_pattern='yyyy-mm-dd')
    entry_date.grid(row=0, column=1, padx=10, pady=5)

    Label(manual_entry_window, text="Description").grid(row=1, column=0, padx=10, pady=5)
    entry_description = Entry(manual_entry_window)
    entry_description.grid(row=1, column=1, padx=10, pady=5)

    Label(manual_entry_window, text="Amount").grid(row=2, column=0, padx=10, pady=5)
    entry_amount = Entry(manual_entry_window)
    entry_amount.grid(row=2, column=1, padx=10, pady=5)

    Label(manual_entry_window, text="Bank").grid(row=3, column=0, padx=10, pady=5)
    bank_var_manual = StringVar(manual_entry_window)
    bank_var_manual.set("Select Bank")  # Default value
    bank_menu_manual = OptionMenu(manual_entry_window, bank_var_manual, *BANKS)
    bank_menu_manual.grid(row=3, column=1, padx=10, pady=5)

    Button(manual_entry_window, text="Save", command=save_manual_entry).grid(row=4, column=0, columnspan=2, pady=10)

# Placeholder function to open HTML (to be implemented)
def open_html():
    pass

# Function to close the application
def cancel():
    root.destroy()

# GUI Setup
root = Tk()
root.title("Finance Manager")

# Dropdown menu for selecting bank
bank_var = StringVar(root)
bank_var.set("Select Bank")  # Default value
bank_menu = OptionMenu(root, bank_var, *BANKS)
bank_menu.pack(padx=20, pady=10)

# Buttons for the GUI
btn_import = Button(root, text="Import CSV", command=import_csv)
btn_import.pack(padx=20, pady=10)

btn_manual_entry = Button(root, text="Manual Entry", command=manual_entry)
btn_manual_entry.pack(padx=20, pady=10)

btn_open_html = Button(root, text="Open HTML", command=open_html)
btn_open_html.pack(padx=20, pady=10)

btn_cancel = Button(root, text="Cancel", command=cancel)
btn_cancel.pack(padx=20, pady=10)

# Start the Tkinter event loop
root.mainloop()


