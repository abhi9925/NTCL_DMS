package com.example.abhishek.myapplication;


public class Person {

    private String Name,Designation,Type,Department,Username,Password,email, PhNo;
    private int parity;

    public Person(String Name,String Designation,String Type,String Department,String Username,String Password,String email, String PhNo)
    {
        if (Name.equals("") || Designation.equals("") || Type.equals("Select Type") || Department.equals("Select Dept") || Username.equals("") || Password.equals("") || email.equals("") || PhNo.equals("")) {
            this.parity=1;
            return;
        }
        this.parity=0;
        this.Name=Name;
        this.Designation=Designation;
        this.Department=Department;
        this.Type=Type;
        this.Username=Username;
        this.Password=Password;
        this.email=email;
        this.PhNo=PhNo;
    }
    public String getName()
    {
        return Name;
    }
    public String getDesignation()
    {
        return Designation;
    }
    public String getType()
    {
        return Type;
    }
    public String getDepartment()
    {
        return Department;
    }
    public String getUsername()
    {
        return Username;
    }
    public String getPassword()
    {
        return Password;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhNo()
    {
        return PhNo;
    }
    public int getParity()
    {
        return  parity;
    }

}
