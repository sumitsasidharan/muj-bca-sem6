## 1. Objective

Enhance the to-do list application by integrating an SQLite database to store the tasks
persistently. This will cover basic database operations in Android.

## 2. Steps to Complete the Experiment

1. Design the Database Schema: Determine the structure of your database, including the tables and columns you will need. For a simple application, you might start with a single table. For
example, a Tasks table for a to-do list app might include columns for ID, TaskName, and Completed.

2. Create a SQLite Helper Class: Extend SQLiteOpenHelper in a new class, e.g., DatabaseHelper. Implement the onCreate and onUpgrade methods to manage database creation and version
management.