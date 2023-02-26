/*
 * Group 11 GUI Database Management System
 * Written by Dylan Vance
 * Database code copied from Lyndsey's DBPro program
 * Group 11 Members: Bronson, Kasim, Lyndsey, Dylan
 * Important Note: Check IDE output terminal for stack trace errors
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GUI implements ActionListener {
    //Database Objects
    private static Connection connect;
    private static Statement stmt;
    
    //GUI Objects
    private JFrame frame = new JFrame("G11 DBMS");
    private JPanel panel = new JPanel();

    //Output Text Area
    private static JTextArea output = new JTextArea(10, 10);

    //Buttons
    private JButton back = new JButton(new AbstractAction("Back") {
        @Override
        public void actionPerformed (ActionEvent e) {
            returnHome();
        }
    });

    private JButton addPerson = new JButton(new AbstractAction("Add Person") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel fNameL = new JLabel("First Name");
            fNameL.setBounds(10, 20, 80, 25);
            JTextField fNameF = new JTextField(20);
            fNameF.setBounds(100, 20, 165, 100);

            JLabel mL = new JLabel("Middle Initial");
            mL.setBounds(10, 20, 80, 25);
            JTextField mF = new JTextField(20);
            mF.setBounds(100, 20, 165, 100);

            JLabel LNameL = new JLabel("Last Name");
            LNameL.setBounds(10, 20, 80, 25);
            JTextField LNameF = new JTextField(20);
            LNameF.setBounds(100, 20, 165, 100);

            JLabel dobL = new JLabel("Date of Birth");
            dobL.setBounds(10, 20, 80, 25);
            JTextField dobF = new JTextField(20);
            dobF.setBounds(100, 20, 165, 100);

            JLabel ssnL = new JLabel("SSN");
            ssnL.setBounds(10, 20, 80, 25);
            JTextField ssnF = new JTextField(20);
            ssnF.setBounds(100, 20, 165, 100);

            JLabel phoneL = new JLabel("Phone Number");
            phoneL.setBounds(10, 20, 80, 25);
            JTextField phoneF = new JTextField(20);
            phoneF.setBounds(100, 20, 165, 100);

            JLabel numL = new JLabel("N-Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            panel.add(fNameL);
            panel.add(fNameF);
            panel.add(mL);
            panel.add(mF);
            panel.add(LNameL);
            panel.add(LNameF);
            panel.add(dobL);
            panel.add(dobF);
            panel.add(ssnL);
            panel.add(ssnF);
            panel.add(phoneL);
            panel.add(phoneF);
            panel.add(numL);
            panel.add(numF);

            JButton submitPerson = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String fName = fNameF.getText();
				    String middle = mF.getText();
				    String LName = LNameF.getText();
				    String dob = dobF.getText();
				    String ssn = ssnF.getText();
                    String phone = phoneF.getText();
				    String nNum = numF.getText();
				    storePerson(fName, middle, LName, dob, ssn, phone, nNum);
				    returnHome();
                }    
            });
            
            panel.add(submitPerson);
            panel.add(back);
        }
    });

    private JButton addDept = new JButton(new AbstractAction("Add Department") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel nameL = new JLabel("Department Name");
            nameL.setBounds(10, 20, 80, 25);
            JTextField nameF = new JTextField(20);
            nameF.setBounds(100, 20, 165, 100);

            JLabel deptL = new JLabel("Department Code");
            deptL.setBounds(10, 20, 80, 25);
            JTextField deptF = new JTextField(20);
            deptF.setBounds(100, 20, 165, 100);

            JLabel cL = new JLabel("College");
            cL.setBounds(10, 20, 80, 25);
            JTextField cF = new JTextField(20);
            cF.setBounds(100, 20, 165, 100);

            JLabel phoneL = new JLabel("Office Phone");
            phoneL.setBounds(10, 20, 80, 25);
            JTextField phoneF = new JTextField(20);
            phoneF.setBounds(100, 20, 165, 100);

            panel.add(nameL);
            panel.add(nameF);
            panel.add(deptL);
            panel.add(deptF);
            panel.add(cL);
            panel.add(cF);
            panel.add(phoneL);
            panel.add(phoneF);

            JButton submitDept = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String name = nameF.getText();
				    String dept = deptF.getText();
				    String college = cF.getText();
				    String phone = phoneF.getText();
				    storeDepartment(name, dept, college, phone);
				    returnHome();
                }    
            });

            panel.add(submitDept);
            panel.add(back);
        }
    });
    
    private JButton addStudent = new JButton(new AbstractAction("Add Student") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            
            JLabel numL = new JLabel("Student N-Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            JLabel sexL = new JLabel("Sex");
            sexL.setBounds(10, 20, 80, 25);
            JTextField sexF = new JTextField(20);
            sexF.setBounds(100, 20, 165, 100);

            JLabel degreeL = new JLabel("Degree");
            degreeL.setBounds(10, 20, 80, 25);
            JTextField degreeF = new JTextField(20);
            degreeF.setBounds(100, 20, 165, 100);

            JLabel classL = new JLabel("Class");
            classL.setBounds(10, 20, 80, 25);
            JTextField classF = new JTextField(20);
            classF.setBounds(100, 20, 165, 100);

            JLabel majorDeptL = new JLabel("Major Department");
            majorDeptL.setBounds(10, 20, 80, 25);
            JTextField majorDeptF = new JTextField(20);
            majorDeptF.setBounds(100, 20, 165, 100);

            JLabel minorDeptL = new JLabel("Minor Department");
            minorDeptL.setBounds(10, 20, 80, 25);
            JTextField minorDeptF = new JTextField(20);
            minorDeptF.setBounds(100, 20, 165, 100);

            JLabel currAddrL = new JLabel("Current Address");
            currAddrL.setBounds(10, 20, 80, 25);
            JTextField currAddrF = new JTextField(20);
            currAddrF.setBounds(100, 20, 165, 100);

            JLabel permCityL = new JLabel("Permanent City");
            permCityL.setBounds(10, 20, 80, 25);
            JTextField permCityF = new JTextField(20);
            permCityF.setBounds(100, 20, 165, 100);

            JLabel permStateL = new JLabel("Permanent State");
            permStateL.setBounds(10, 20, 80, 25);
            JTextField permStateF = new JTextField(20);
            permStateF.setBounds(100, 20, 165, 100);

            JLabel permZipL = new JLabel("Permanent Zip Code");
            permZipL.setBounds(10, 20, 80, 25);
            JTextField permZipF = new JTextField(20);
            permZipF.setBounds(100, 20, 165, 100);

            panel.add(numL);
            panel.add(numF);
            panel.add(sexL);
            panel.add(sexF);
            panel.add(degreeL);
            panel.add(degreeF);
            panel.add(classL);
            panel.add(classF);
            panel.add(majorDeptL);
            panel.add(majorDeptF);
            panel.add(minorDeptL);
            panel.add(minorDeptF);
            panel.add(currAddrL);
            panel.add(currAddrF);
            panel.add(permCityL);
            panel.add(permCityF);
            panel.add(permStateL);
            panel.add(permStateF);
            panel.add(permZipL);
            panel.add(permZipF);
            
            JButton submitStudent = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String num = numF.getText();
				    String sex = sexF.getText();
				    String degree = degreeF.getText();
				    String sClass = classF.getText();
				    String major = majorDeptF.getText();
				    String minor = minorDeptF.getText();
                    String addr = currAddrF.getText();
                    String city = permCityF.getText();
                    String state = permStateF.getText();
                    String zip = permZipF.getText();
				    storeStudent(num, sex, degree, sClass, major, minor, addr, city, state, zip);
				    returnHome();
                }    
            });
            
            panel.add(submitStudent);
            panel.add(back);
        }
    });

    private JButton addInstructor = new JButton(new AbstractAction("Add Instructor") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel numL = new JLabel("Instructor N-Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            JLabel currAddrL = new JLabel("Current Address");
            currAddrL.setBounds(10, 20, 80, 25);
            JTextField currAddrF = new JTextField(20);
            currAddrF.setBounds(100, 20, 165, 100);

            JLabel phoneL = new JLabel("Phone Number");
            phoneL.setBounds(10, 20, 80, 25);
            JTextField phoneF = new JTextField(20);
            phoneF.setBounds(100, 20, 165, 100);

            JLabel ageL = new JLabel("Age");
            ageL.setBounds(10, 20, 80, 25);
            JTextField ageF = new JTextField(20);
            ageF.setBounds(100, 20, 165, 100);

            JLabel deptL = new JLabel("Department Code");
            deptL.setBounds(10, 20, 80, 25);
            JTextField deptF = new JTextField(20);
            deptF.setBounds(100, 20, 165, 100);

            panel.add(numL);
            panel.add(numF);
            panel.add(currAddrL);
            panel.add(currAddrF);
            panel.add(phoneL);
            panel.add(phoneF);
            panel.add(ageL);
            panel.add(ageF);
            panel.add(deptL);
            panel.add(deptF);
            
            JButton submitInstructor = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String num = numF.getText();
                    String addr = currAddrF.getText();
                    String phone = phoneF.getText();
                    String age = ageF.getText();
                    String dept = deptF.getText();
				    storeInstructor(num, addr, phone, age, dept);
				    returnHome();
                }    
            });
            
            panel.add(submitInstructor);
            panel.add(back);
        }
    });

    private JButton addCourse = new JButton(new AbstractAction("Add Course") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel numL = new JLabel("Course Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            JLabel nameL = new JLabel("Course Name");
            nameL.setBounds(10, 20, 80, 25);
            JTextField nameF = new JTextField(20);
            nameF.setBounds(100, 20, 165, 100);

            JLabel levelL = new JLabel("Course Level");
            levelL.setBounds(10, 20, 80, 25);
            JTextField levelF = new JTextField(20);
            levelF.setBounds(100, 20, 165, 100);

            JLabel hoursL = new JLabel("Course Hours");
            hoursL.setBounds(10, 20, 80, 25);
            JTextField hoursF = new JTextField(20);
            hoursF.setBounds(100, 20, 165, 100);

            JLabel deptL = new JLabel("Department Code");
            deptL.setBounds(10, 20, 80, 25);
            JTextField deptF = new JTextField(20);
            deptF.setBounds(100, 20, 165, 100);

            JLabel descL = new JLabel("Course Description");
            descL.setBounds(10, 20, 80, 25);
            JTextField descF = new JTextField(20);
            descF.setBounds(100, 20, 165, 100);

            panel.add(numL);
            panel.add(numF);
            panel.add(nameL);
            panel.add(nameF);
            panel.add(levelL);
            panel.add(levelF);
            panel.add(hoursL);
            panel.add(hoursF);
            panel.add(deptL);
            panel.add(deptF);
            panel.add(descL);
            panel.add(descF);
            
            JButton submitCourse = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String num = numF.getText();
                    String name = nameF.getText();
                    String level = levelF.getText();
                    String hours = hoursF.getText();
                    String dept = deptF.getText();
                    String desc = descF.getText();
				    storeCourse(num, name, level, hours, dept, desc);
				    returnHome();
                }    
            });
            
            panel.add(submitCourse);
            panel.add(back);
        }
    });

    private JButton addSection = new JButton(new AbstractAction("Add Section") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel cNumL = new JLabel("Course Number");
            cNumL.setBounds(10, 20, 80, 25);
            JTextField cNumF = new JTextField(20);
            cNumF.setBounds(100, 20, 165, 100);

            JLabel sNumL = new JLabel("Section Number");
            sNumL.setBounds(10, 20, 80, 25);
            JTextField sNumF = new JTextField(20);
            sNumF.setBounds(100, 20, 165, 100);

            JLabel iNumL = new JLabel("Instructor Number");
            iNumL.setBounds(10, 20, 80, 25);
            JTextField iNumF = new JTextField(20);
            iNumF.setBounds(100, 20, 165, 100);

            JLabel semL = new JLabel("Semester");
            semL.setBounds(10, 20, 80, 25);
            JTextField semF = new JTextField(20);
            semF.setBounds(100, 20, 165, 100);

            JLabel yearL = new JLabel("Year");
            yearL.setBounds(10, 20, 80, 25);
            JTextField yearF = new JTextField(20);
            yearF.setBounds(100, 20, 165, 100);

            panel.add(cNumL);
            panel.add(cNumF);
            panel.add(sNumL);
            panel.add(sNumF);
            panel.add(iNumL);
            panel.add(iNumF);
            panel.add(semL);
            panel.add(semF);
            panel.add(yearL);
            panel.add(yearF);
            
            JButton submitSection = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String cnum = cNumF.getText();
                    String snum = sNumF.getText();
                    String inum = iNumF.getText();
                    String semester = semF.getText();
                    String year = yearF.getText();
				    storeSection(cnum, snum, inum, semester, year);
				    returnHome();
                }    
            });
            
            panel.add(submitSection);
            panel.add(back);
        }
    });

    private JButton addEnrollment = new JButton(new AbstractAction("Add Enrollment") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            
            JLabel numL = new JLabel("Student N-Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            JLabel cNumL = new JLabel("Course Number");
            cNumL.setBounds(10, 20, 80, 25);
            JTextField cNumF = new JTextField(20);
            cNumF.setBounds(100, 20, 165, 100);

            JLabel sNumL = new JLabel("Section Number");
            sNumL.setBounds(10, 20, 80, 25);
            JTextField sNumF = new JTextField(20);
            sNumF.setBounds(100, 20, 165, 100);

            panel.add(numL);
            panel.add(numF);
            panel.add(cNumL);
            panel.add(cNumF);
            panel.add(sNumL);
            panel.add(sNumF);
            
            JButton submitEnrollment = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String num = numF.getText();
                    String cnum = cNumF.getText();
                    String snum = sNumF.getText();
				    storeEnrollment(num, cnum, snum);
				    returnHome();
                }    
            });
            
            panel.add(submitEnrollment);
            panel.add(back);
        }
    });

    private JButton addGrade = new JButton(new AbstractAction("Alter Grade") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel numL = new JLabel("Student N-Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            JLabel cNumL = new JLabel("Course Number");
            cNumL.setBounds(10, 20, 80, 25);
            JTextField cNumF = new JTextField(20);
            cNumF.setBounds(100, 20, 165, 100);

            JLabel sNumL = new JLabel("Section Number");
            sNumL.setBounds(10, 20, 80, 25);
            JTextField sNumF = new JTextField(20);
            sNumF.setBounds(100, 20, 165, 100);
            
            JLabel gradeL = new JLabel("Letter Grade");
            gradeL.setBounds(10, 20, 80, 25);
            JTextField gradeF = new JTextField(20);
            gradeF.setBounds(100, 20, 165, 100);

            JLabel pointsL = new JLabel("GPA");
            pointsL.setBounds(10, 20, 80, 25);
            JTextField pointsF = new JTextField(20);
            pointsF.setBounds(100, 20, 165, 100);

            panel.add(numL);
            panel.add(numF);
            panel.add(cNumL);
            panel.add(cNumF);
            panel.add(sNumL);
            panel.add(sNumF);
            panel.add(gradeL);
            panel.add(gradeF);
            panel.add(pointsL);
            panel.add(pointsF);

            JButton submitGrade = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String num = numF.getText();
                    String cnum = cNumF.getText();
                    String snum = sNumF.getText();
                    String grade = gradeF.getText();
                    String points = pointsF.getText();
				    alterGrade(num, cnum, snum, grade, points);
				    returnHome();
                }    
            });
            
            panel.add(submitGrade);
            panel.add(back);
        }
    });

    private JButton getGrade = new JButton(new AbstractAction("Grade Report") {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel numL = new JLabel("Student N-Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            panel.add(numL);
            panel.add(numF);

            JButton submitStudentGrade = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String num = numF.getText();
                    output.setText("");
				    getGradeReport(num);
                }    
            });

            panel.add(submitStudentGrade);
            panel.add(back);
        }
    });

    private JButton getCourses = new JButton(new AbstractAction("Student's Courses") {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel numL = new JLabel("Student N-Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            panel.add(numL);
            panel.add(numF);

            JButton submitStudentCourses = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String num = numF.getText();
				    output.setText("");
                    getCourses(num);
                }    
            });

            panel.add(submitStudentCourses);
            panel.add(back);
        }
    });

    private JButton getCoursesOffered = new JButton(new AbstractAction("Courses Offered") {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            JLabel nameL = new JLabel("Department Name");
            nameL.setBounds(10, 20, 80, 25);
            JTextField nameF = new JTextField(20);
            nameF.setBounds(100, 20, 165, 100);

            JLabel orL = new JLabel("OR");
            orL.setBounds(10, 20, 80, 25);

            JLabel codeL = new JLabel("Department Code");
            codeL.setBounds(10, 20, 80, 25);
            JTextField codeF = new JTextField(20);
            codeF.setBounds(100, 20, 165, 100);

            panel.add(nameL);
            panel.add(nameF);
            panel.add(orL);
            panel.add(codeL);
            panel.add(codeF);

            JButton submitCoursesOffered = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String name = nameF.getText();
                    String code = codeF.getText();
                    output.setText("");
                    getCoursesOffered(name, code);
                }    
            });

            panel.add(submitCoursesOffered);
            panel.add(back);
        }
    });

    private JButton getInstructorCourses = new JButton(new AbstractAction("Instructor's Courses") {
        @Override
        public void actionPerformed (ActionEvent e) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            
            JLabel numL = new JLabel("Instructor N-Number");
            numL.setBounds(10, 20, 80, 25);
            JTextField numF = new JTextField(20);
            numF.setBounds(100, 20, 165, 100);

            panel.add(numL);
            panel.add(numF);

            JButton submitInstructorCourses = new JButton(new AbstractAction("Submit") {
                @Override
                public void actionPerformed (ActionEvent e) {
                    String num = numF.getText();
                    output.setText("");
                    getInstructorCourses(num);
                }    
            });

            panel.add(submitInstructorCourses);
            panel.add(back);
        }
    });

    //Constructor
    public GUI() {
        panel.setBorder(BorderFactory.createEmptyBorder(50, 500, 50, 500));
        panel.setLayout(new GridLayout(22, 1));
        returnHome();
        frame.add(panel, BorderLayout.CENTER);
        frame.add(output, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.pack();
        frame.setVisible(true);
    }

    //Print Output to Text Area Function
    private static void printOutput(String str, boolean newLine) {
        output.append(str);
        if (newLine) { output.append("\n"); }
    }

    //Reset Function
    private void returnHome() {
		panel.removeAll();
        panel.revalidate();
        panel.repaint();
		panel.add(addPerson);
        panel.add(addDept);
		panel.add(addStudent);
		panel.add(addInstructor);
        panel.add(addCourse);
        panel.add(addSection);
        panel.add(addEnrollment);
        panel.add(addGrade);
        panel.add(getGrade);
        panel.add(getCourses);
        panel.add(getCoursesOffered);
        panel.add(getInstructorCourses);
        frame.add(output, BorderLayout.SOUTH);
	}

    //Storing Functions
    private void storePerson(String Fname, String Minit, String Lname, String DOB, String Ssn, String Phone, String nNumber) {  
        char m = Minit.charAt(0);
        try {
            stmt.executeUpdate("INSERT INTO PERSON (Fname, Minit, Lname, DOB, Ssn, Phone, Nnumber)" +
                "VALUES('" + Fname + "','" + m + "','" + Lname + "','" + DOB + "','" + Ssn + "','" + Phone + "','"
                + nNumber + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Person Addition Failed.");
            return;
        }
        System.out.println("Person Added Successfully.");
    }

    private void storeDepartment(String D_name, String Dept_code, String College, String Office_phone) {
        try {
            stmt.executeUpdate("INSERT INTO DEPARTMENT(D_name, Dept_code, College, Office_phone)" +
                        "VALUES('" + D_name + "','" + Dept_code + "','" + College + "','" + Office_phone + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Department Addition Failed.");
            return;
        }
        System.out.println("Department Added Successfully.");
    }

    private void storeStudent(String nNumber, String StudentSex, String degree, String Stuclass, String majorDept, String minorDept, String curAddress, String permCity, String permState, String permZip) {
        try {
            stmt.executeUpdate(
                "INSERT INTO STUDENT (S_Nnumber, Sex, CurrentAddr, Degree, Class, MajorDept, MinorDept, PermCity, PermState, PermZipCode)"
                        + "VALUES('" + nNumber + "','" + StudentSex + "','" + curAddress + "','" + degree + "','"
                        + Stuclass + "','" + majorDept + "','" + minorDept + "','" + permCity + "','" + permState
                        + "','" + permZip + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Student Addition Failed.");
            return;
        }
        System.out.println("Student Added Successfully.");
    }

    private void storeInstructor(String InNumber, String IAddress, String IOffice_num, String IAge, String ID_code) {
        try {
            stmt.executeUpdate("INSERT INTO INSTRUCTOR (I_Nnumber, D_Code, I_adress, Office_num, I_age)" +
                        "VALUES('" + InNumber + "','" + ID_code + "','" + IAddress + "','" + IOffice_num + "','" + IAge + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Instructor Addition Failed.");
            return;
        }
        System.out.println("Instructor Added Successfully.");
    }

    private void storeCourse(String Course_num, String Dcode, String C_level, String C_hours, String dept, String Description) {
        try {
            stmt.executeUpdate("INSERT INTO COURSE(Course_num, Dcode, C_name, Description, C_level, C_hours)" +
                        "VALUES('" + Course_num + "','" + Dcode + "','" + "','" + Description + "','" + C_level + "','" + C_hours + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Course Addition Failed.");
            return;
        }
        System.out.println("Course Added Successfully.");
    }

    private void storeSection(String SC_num, String section_num, String i_num, String semester, String S_year) {
        try {
            stmt.executeUpdate("INSERT INTO SECTION(C_num, Section_num, I_num, Semester, S_year)" +
                        "VALUES('" + SC_num + "','" + section_num + "','" + i_num + "','" + semester + "','" + S_year + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Section Addition Failed.");
            return;
        }
        System.out.println("Section Added Successfully.");
    }

    private void storeEnrollment(String Student_Nnumber, String course_number, String section_number) {
        try {
            stmt.executeUpdate("INSERT INTO ENROLLED_IN(Stud_num, Cour_num, Sec_num)" +
                "VALUES('" + Student_Nnumber + "','" + course_number + "','" + section_number
                + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Enrollment Addition Failed.");
            return;
        }
        System.out.println("Enrollment Added Successfully.");
    }

    private void alterGrade(String GStudent_Nnumber, String Gcourse_number, String Gsection_number, String letter_grade, String grade_points) {
        try {
            PreparedStatement ps = null;
            ps = connect.prepareStatement("UPDATE ENROLLED_IN SET Letter_grade = '" + letter_grade
                + "', Points = '" + grade_points + "' where stud_num = '" + GStudent_Nnumber
                + "' AND cour_num = '" + Gcourse_number + "' AND sec_num = '" + Gsection_number + "' ");
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Grade Alter Failed.");
            return;
        }
        System.out.println("Enrollment Altered Successfully.");
    }

    /* TO DO:
     * Courses Offered
     * Instructor List
     */
    //Getters
    private void getGradeReport(String nNumber) {
        returnHome();
        PreparedStatement ps1 = null;
        try {
            ps1 = connect.prepareStatement("SELECT (SUM(POINTS) / COUNT(COUR_NUM)) AS GPA, person.nnumber, person.Fname, person.Minit, "+
            "person.Lname, person.dob, person.phone, person.ssn FROM ENROLLED_IN JOIN PERSON ON ENROLLED_IN.Stud_num = PERSON.nnumber WHERE STUD_NUM = '"
            +nNumber+"'GROUP BY person.nnumber, person.fname, person.Minit, person.Lname, person.dob, person.phone, person.ssn");
            ps1.execute();
            ResultSet resultSet = ps1.getResultSet();
            while(resultSet.next()){
                Float GPA = Float.valueOf(resultSet.getString("GPA"));
                String Nnumber = resultSet.getString("Nnumber");
                String Fname = resultSet.getString("Fname");
                String Minit = resultSet.getString("Minit");
                String Lname = resultSet.getString("Lname");
                String DOB = resultSet.getString("DOB");
                String Ssn = resultSet.getString("Ssn");
                String Phone = resultSet.getString("Phone");
                printOutput(Fname, false);
                printOutput(" ", false);
                printOutput(Minit, false);
                printOutput(" ", false);
                printOutput(Lname, true);
                printOutput(DOB, true);
                printOutput("SSN: ", false);
                printOutput(Ssn, true);
                printOutput("N-Number: ", false);
                printOutput(Nnumber, true);
                printOutput("Phone Number: ", false);
                printOutput(Phone, true);
                printOutput("Cumulative GPA: ", false);
                printOutput(GPA.toString(), true);
                printOutput("", true);
            }
        }
        catch(Exception e) {
            printOutput("Grade Report Fetch Failed", true);
            e.printStackTrace();
        }
        getCourses(nNumber);
    }

    private void getCourses(String nNumber) {
        returnHome();
        PreparedStatement ps8 = null;
        try  {
            ps8 = connect.prepareStatement("SELECT cour_num, sec_num, letter_grade, points FROM ENROLLED_IN WHERE STUD_NUM = '"+nNumber+"'");
            ps8.execute();
            ResultSet resultSet = ps8.getResultSet();
            while(resultSet.next()){
                String cour_num = resultSet.getString("cour_num");
                String sec_num = resultSet.getString("sec_num");
                String letter_grade = resultSet.getString("letter_grade");
                String points = resultSet.getString("points");
                printOutput("Course: ", false);
                printOutput(cour_num, true);
                printOutput("Section Number: ", false);
                printOutput(sec_num, true);
                printOutput("Letter Grade:  ", false);
                printOutput(letter_grade, true);
                printOutput("Points:  ", false);
                printOutput(points, true);
                printOutput("", true);
            }
        }
        catch(Exception e) {
            printOutput("Student Courses Fetch Failed", true);
            e.printStackTrace();
        }
    }

    private void getCoursesOffered(String name, String code) {
        if (name.length() != 0) {
            returnHome();
            try {
                PreparedStatement ps2 = null;
                ps2 = connect.prepareStatement("SELECT Course_num, C_name FROM Course JOIN DEPARTMENT ON COURSE.DCODE = DEPARTMENT.DEPT_CODE WHERE DEPARTMENT.D_NAME = '" +name+"' ");
                ps2.execute();
                ResultSet resultSet = ps2.getResultSet();

                while(resultSet.next()){
                    String course_number = resultSet.getString("Course_num");
                    String course_name = resultSet.getString("C_name");
                    printOutput("Course Number: ", false);
                    printOutput(course_number, true);
                    printOutput("Course Name: ", false);
                    printOutput(course_name, true);
                    printOutput("", true);
                }
            }
            catch(Exception e) {
                printOutput("Courses Offered Fetch Failed", true);
                e.printStackTrace();
            }
            return;
        }
        else if (code.length() != 0) {
            returnHome();
            try {
                PreparedStatement ps3 = null;
                ps3 = connect.prepareStatement("SELECT Course_num, C_name FROM Course WHERE DCODE = '" +code+"' ");
                ps3.execute();
                ResultSet resultSet2 = ps3.getResultSet();

                while(resultSet2.next()){
                    String course_number = resultSet2.getString("Course_num");
                    String course_name = resultSet2.getString("C_name");
                    printOutput("Course Number: ", false);
                    printOutput(course_number, true);
                    printOutput("Course Name: ", false);
                    printOutput(course_name, true);
                    printOutput("", true);
                }
            }
            catch(Exception e) {
                printOutput("Courses Offered Fetch Failed", true);
                e.printStackTrace();
            }
            return;
        }
        else {
            returnHome();
            printOutput("Courses Offered Fetch Failed", true);
        }
    }

    private void getInstructorCourses(String num) {
        returnHome();
        PreparedStatement ps4 = null;
        try {
            ps4 = connect.prepareStatement("SELECT c_num, section_num FROM SECTION WHERE I_NUM = '" +num+ "' ");
            ps4.execute();
            ResultSet resultSet = ps4.getResultSet();
            while(resultSet.next()){
                String course_number = resultSet.getString("C_NUM");
                String section_number = resultSet.getString("section_num");
                printOutput("Course Number: ", false);
                printOutput(course_number, true);
                printOutput("Section Number: ", false);
                printOutput(section_number, true);
            }
        }
        catch (Exception e) {
            printOutput("Instructor's Courses Fetch Failed", true);
            e.printStackTrace();
        }
    }

    //Ignore This
    @Override
    public void actionPerformed(ActionEvent e) {/* Nothing to see here */}

    //Main
    public static void main (String args[]) {
        // Username for sql server
        String username = "G11";
        // Password for sql server
        String password = "Fall2022G11";
        // URL for server
        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";

        //Connection Code
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        }
        catch (SQLException e) {
            printOutput("Connection Failed", true);
            e.printStackTrace();
        }

        try {
            connect = DriverManager.getConnection(url, username, password);
            boolean reachable = connect.isValid(5);

            if (reachable) {
                printOutput("Connection Successful", true);
                stmt = connect.createStatement();
            }
        }
        catch (SQLException e) {
            printOutput("Connection Failed", true);
            e.printStackTrace();
        }

        //The line that does it all
        new GUI();
    }
}