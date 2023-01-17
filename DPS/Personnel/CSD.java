package Personnel;

import java.util.*;
public class CSD {
	
	

		protected String FirstName;
		protected String LastName;
		protected String FullName;
		protected int age;
		protected String gender;
		protected String address;

		static int EmployeeId = 100;
		static int studentId = 1000;
		static int UGradNum = 0;
		static int GradNum = 0;
		static int FacNum = 0;
		static int ProgDir = 0;
		static int chairma = 0;

		ChairPerson ceo;
		List<Faculty> listOfFac = new ArrayList<Faculty>();
		List<UGrad> underGrad = new ArrayList<UGrad>();
		List<Grad> grad = new ArrayList<Grad>();
		List<ProgramDirector> pdir = new ArrayList<ProgramDirector>();
		

		/**
		 * This is the default constructor for this class
		 */
		public CSD() {
			FirstName = "";
			LastName = "";
			age = 0;
			gender = "";
			address = "";
		}

		public int compareTo(Faculty o) {
			// TODO Auto-generated method stub
			return 0;

		}

		/**
		 * This is a copy constructor with ChairPerson data
		 * 
		 * @param chair
		 */
		public CSD(ChairPerson chair) {// copy constructor of with ChairPerson info
			// TODO Auto-generated constructor stub
			ceo=chair;

		}

		/**
		 * This is a getter method for ChairPerson
		 * 
		 * @return a ChairPerson object
		 */
		public ChairPerson getChairPerson() { // get ChairPerson method
			// TODO Auto-generated method stub
			return ceo;
		}

		// add faculty member method
		/**
		 * This method add the faculty information to the university faculty records.
		 * The new hired faculty is assigned to the designated Program Director
		 * according to the specialty of Faculty members . The hired faculty members
		 * will also be added to the Program Director record . The method doesn't add
		 * the same object twice(No duplicates)
		 * 
		 * @param f (Faculty Object)
		 * @throws NoSpaceException
		 */
		public void HireFaculty(Faculty f) throws NoSpaceException {
			// TODO Auto-generated method stub
			
		
			if (!listOfFac.contains(f)) {
				listOfFac.add(f);
				FacNum = listOfFac.size();

			}
			if (FacNum > 70) {
				throw new NoSpaceException("There is to many  members");
			}
			if (pdir != null && pdir.size() > 0) {

				for (ProgramDirector p : pdir) {
					if (p.getFaculty() == null) {
						p.setFaculty(new ArrayList<>());
					}

					if (p.getFaculty().size() < 35 && f.getProgramDirector() == null && p.program.equals(f.program)) {
						p.getFaculty().add(f);
						f.setProgramDirector(p);

						break;
					}
				}
			}
			for(ProgramDirector pe: pdir) {
				if(pe.getFaculty().size()>25) {
					throw new NoSpaceException("Too much to assign");
				}
			}
			

		}

		/**
		 * This method returns a list of faculty
		 * 
		 * @return list of Faculty members
		 */
		public List<Faculty> getFaculty() {
			// TODO Auto-generated method stub
			return listOfFac;
		}

		/**
		 * this method returns number of faculty members
		 * 
		 * @return an integer representing number of faculty members
		 */
		public int getNumOfFaculty() {
			// TODO Auto-generated method stub
			return FacNum;
		}

		/**
		 * This method add student object information into the list of students It also
		 * assigns a faculty to every new admitted student. The student will also be
		 * added to faculty record The students are assigned to faculty on a
		 * first-come-first-serve record The method does not added the same student
		 * twice(No duplicates)
		 * 
		 * @param s
		 * @throws NoSpaceException
		 */
		public void AdmitStudent(UGrad s) throws NoSpaceException {
			// TODO Auto-generated method stub

			

			if (!underGrad.contains(s)) {
				underGrad.add(s);
				UGradNum = underGrad.size();
			}
			if (UGradNum > 500) {
				throw new NoSpaceException("There is to many students");
			}
			
			if (listOfFac != null && listOfFac.size() > 0) {

				for (Faculty fa : listOfFac) {
					if (fa.getAdvisingUgrads() == null) {
						fa.setAdvisingUgrads(new ArrayList<>());
					}

					if (fa.getAdvisingUgrads().size() < 8 && s.getAdvisor() == null) {
						fa.getAdvisingUgrads().add(s);
						s.setAdvisor(fa);
						break;
					}
				}
			}

			for (ProgramDirector pe : pdir) {
				if (pe.getFaculty() != null && pe.getFaculty().size() > 0) {
					for (Faculty fa2 : pe.getFaculty()) {
						if (fa2.getAdvisingUgrads() == null) {
							fa2.setAdvisingUgrads(new ArrayList<>());
						}

						if (fa2.getAdvisingUgrads().size() < 8 && s.getAdvisor() == null) {
							fa2.getAdvisingUgrads().add(s);

							break;
						}
					}
				}
			}

		}

		/**
		 * This method returns the number of undergraduate students
		 * 
		 * @return an integer represents the number of undergraduate students
		 */
		public int getNumOfUGradStudents() {
			// TODO Auto-generated method stub

			return UGradNum;
		}

		/**
		 * This method admits a new graduate student This method add the graduate
		 * student information to the list of graduate student This method assigned the
		 * grad student as a TA to a faculty that has not exceeded the Tas limit The TAs
		 * are assigned on a first-come-first-serve basis
		 * 
		 * @param s
		 * @throws NoSpaceException
		 */
		public void HireTA(Grad s) throws NoSpaceException {
			// TODO Auto-generated method stub
			
			if (!grad.contains(s)) {
				grad.add(s);
				GradNum = grad.size();
			}
			if(GradNum>150) {
				throw new NoSpaceException("Number of TAs exceeded the limit!");	
			}
			
			if (listOfFac != null && listOfFac.size() > 0) {

				for (Faculty fa : listOfFac) {
					if (fa.getTAs() == null) {
						fa.setTAs(new ArrayList<>());
					}

					if (listOfFac.size() == (listOfFac.indexOf(fa) - 1) && fa.getTAs().size() > 5) {
						throw new NoSpaceException("There is no space left!");

					}
					if (fa.getTAs().size() < 5 && s.getAdvisor() == null) {
						fa.getTAs().add(s);
						s.setAdvisor(fa);
						break;
					}
				}

				for (ProgramDirector pe : pdir) {
					for (Faculty fa2 : pe.getFaculty()) {
						if (fa2.getTAs() == null) {
							fa2.setTAs(new ArrayList<>());
						}

						if (pe.getFaculty().size() == (pe.getFaculty().indexOf(fa2) - 1) && fa2.getTAs().size() > 5) {
							throw new NoSpaceException("There is no space left!");

						}
						if (fa2.getTAs().size() < 5 && s.getAdvisor() == null) {
							fa2.getTAs().add(s);

							break;
						}
					}
				}

			}
		}

		/**
		 * this method returns the number of graduate student
		 * 
		 * @return an integer represents the number of grad student
		 */
		public Integer getNumOfGradStudents() {
			// TODO Auto-generated method stub
			return GradNum;
		}

		/**
		 * This method clear/remove the student information from the record
		 * 
		 * @param s1
		 * @throws NoTAException
		 */
		public void AlumnusGrad(Grad s1) throws NoTAException {
			// TODO Auto-generated method stub

			for (Faculty fa : listOfFac) {
				boolean removed = false;

				if (fa != null && fa.getTAs().size() > 0 && fa.getTAs().contains(s1)) {
					fa.getTAs().remove(s1);
					grad.remove(s1);
					removed = true;
				} else {
					continue;
				}

				GradNum = grad.size();
				if (fa.getTAs().isEmpty() || fa.getNumOfTAs() == 0) {
					throw new NoTAException("There is no TA");
				}
				if (removed) {
					break;
				}
			}

			for (ProgramDirector pe : pdir) {
				for (Faculty fa2 : pe.getFaculty()) {
					boolean removed = false;

					if (fa2 != null && fa2.getTAs().size() > 0 && fa2.getTAs().contains(s1)) {
						fa2.getTAs().remove(s1);

						removed = true;
					} else {
						continue;
					}

					if (fa2.getTAs().isEmpty() || fa2.getNumOfTAs() == 0) {
						throw new NoTAException("There is no TA");
					}
					if (removed) {
						break;
					}
				}
			}

		}

		/**
		 * This method clear/remove the student information from the record
		 * 
		 * @param s
		 */
		public void AlumnusUGrad(UGrad s) {
			// TODO Auto-generated method stub
			underGrad.remove(s);
			UGradNum = underGrad.size();
			for (Faculty fa : listOfFac) {
				if (fa.getAdvisingUgrads().contains(s) && fa.getAdvisingUgrads().size() <= 8) {
					fa.getAdvisingUgrads().remove(s);

				}
			}

			for (ProgramDirector pe : pdir) {
				for (Faculty fa2 : pe.getFaculty()) {
					boolean removed = false;

					if (fa2.getAdvisingUgrads().contains(s) && fa2.getAdvisingUgrads().size() <= 8) {
						fa2.getAdvisingUgrads().remove(s);

					}
				}
			}

		}

		/**
		 * This method returns a alphabetical sorted list according to the FullName of
		 * each element
		 * 
		 * @return list of grad students
		 */
		public List<Grad> ExtractAllGradDetails() {
			// TODO Auto-generated method stub
			for (Grad graduates : grad) {
				graduates.FullName = graduates.FirstName.concat(graduates.LastName);

			}
			for (int i = 0; i < grad.size() - 1; i++) {
				for (int j = i + 1; j < grad.size(); j++) {
					if (grad.get(j).FullName.compareTo(grad.get(i).FullName) < 0) {
						Collections.swap(grad, i, j);
					}
				}
			}
			return grad;
		}

		/**
		 * This method returns a alphabetical sorted list according to the FullName of
		 * each element
		 * 
		 * @return the list of undergrad students
		 */
		public List<UGrad> ExtractAllUGradDetails() {
			// TODO Auto-generated method stub
			for (UGrad student : underGrad) {
				student.FullName = student.FirstName.concat(student.LastName);

			}
			for (int i = 0; i < underGrad.size() - 1; i++) {
				for (int j = i + 1; j < underGrad.size(); j++) {
					if (underGrad.get(j).FullName.compareTo(underGrad.get(i).FullName) < 0) {
						Collections.swap(underGrad, i, j);
					}
				}
			}
			return underGrad;
		}

		/**
		 * This method returns a alphabetical sorted list according to the FullName of
		 * each element
		 * 
		 * @return the list of faculty members
		 */
		public List<Faculty> ExtractAllFacultyDetails() {
			// TODO Auto-generated method s

			for (Faculty pro : listOfFac) {
				pro.FullName = pro.FirstName.concat(pro.LastName);

			}
			for (int i = 0; i < listOfFac.size() - 1; i++) {
				for (int j = i + 1; j < listOfFac.size(); j++) {
					if (listOfFac.get(j).FullName.compareTo(listOfFac.get(i).FullName) < 0) {
						Collections.swap(listOfFac, i, j);
					}
				}
			}

			return listOfFac;
		}

		/**
		 * This method extract the information of faculty member belong to a specific
		 * program This method returns a alphabetical sorted list according to the
		 * FullName of each element
		 * 
		 * @param program
		 * @return a list of faculty members
		 */
		public List<Faculty> ExtractFacultyDetails(String program) {
			// TODO Auto-generated method stub
			List<Faculty> programFac = new ArrayList<Faculty>();
			for (Faculty pro : listOfFac) {
				if (pro.program.equals(program)) {
					programFac.add(pro);
				}
			}
			for (Faculty pro : programFac) {
				pro.FullName = pro.FirstName.concat(pro.LastName);

			}
			for (int i = 0; i < programFac.size() - 1; i++) {
				for (int j = i + 1; j < programFac.size(); j++) {
					if (programFac.get(j).FullName.compareTo(programFac.get(i).FullName) < 0) {
						Collections.swap(programFac, i, j);
					}
				}
			}
			return programFac;
		}

		/**
		 * This method extract the information of undergrad students belong to a
		 * specific faculty member This method returns a alphabetical sorted list
		 * according to the FullName of each element
		 * 
		 * @param f1
		 * @return a list of undergrad students
		 */
		public List<UGrad> ExtractAdviseesDetails(Faculty f1) {
			// TODO Auto-generated method stub
			for (UGrad student : f1.getAdvisingUgrads()) {
				student.FullName = student.FirstName.concat(student.LastName);

			}
			for (int i = 0; i < f1.getAdvisingUgrads().size() - 1; i++) {
				for (int j = i + 1; j < f1.getAdvisingUgrads().size(); j++) {
					if (f1.getAdvisingUgrads().get(j).FullName.compareTo(f1.getAdvisingUgrads().get(i).FullName) < 0) {
						Collections.swap(f1.getAdvisingUgrads(), i, j);
					}
				}
			}
			return f1.getAdvisingUgrads();
		}

		/**
		 * This method extract the information of grad students belong to a specific
		 * faculty member This method returns a alphabetical sorted list according to
		 * the FullName of each element
		 * 
		 * @param f1
		 * @return the list of grad students
		 */
		public List<Grad> ExtractTAsDetails(Faculty f1) {
			// TODO Auto-generated method stub
			for (Grad graduates : f1.getTAs()) {
				graduates.FullName = graduates.FirstName.concat(graduates.LastName);

			}
			for (int i = 0; i < f1.getTAs().size() - 1; i++) {
				for (int j = i + 1; j < f1.getTAs().size(); j++) {
					if (f1.getTAs().get(j).FullName.compareTo(f1.getTAs().get(i).FullName) < 0) {
						Collections.swap(f1.getTAs(), i, j);
					}
				}
			}
			return f1.getTAs();
		}

		/**
		 * This method add a program director to a list of program directors It assigned
		 * the program director to a chairperson It also added the program director to
		 * chairperson record
		 * 
		 * @param p1
		 * @throws NoSpaceException
		 */
		public void addProgramDirector(ProgramDirector p1) throws NoSpaceException {
			// TODO Auto-generated method stub
			

			if (!pdir.contains(p1)) {
				pdir.add(p1);
				ProgDir = pdir.size();
			}
			if (ProgDir > 3) {
				throw new NoSpaceException("There is to many students");
			}
			
			if (listOfFac != null && listOfFac.size() > 0) {

				if (ceo.getProgramDirector() == null) {
					ceo.setProgramDirector(new ArrayList<>());
				}

				if (ceo.getProgramDirector().size() < 3 && ceo.getProgramDirector() == null) {
					ceo.getProgramDirector().add(p1);
					p1.setChairPerson(ceo);

				}

			}
		}

		/**
		 * This method remove the faculty member's information from the university
		 * faculty record The designated program director will assign the students of
		 * the retired faculty to the next available faculty member Reassigns the TAs
		 * for the next available faculty
		 * 
		 * @param f1
		 * @throws NoSpecialtyException
		 * @throws NoSpaceException
		 */
		public void RetireFaculty(Faculty f1) throws NoSpecialtyException, NoSpaceException {
			// TODO Auto-generated method stub
			

			if (f1 != null && f1.getTAs() != null && f1.getTAs().size() > 0) {
				for (Grad g : f1.getTAs()) {
					for (Faculty fa : listOfFac) {
						if (fa.equals(f1)) {
							continue;
						} // check if fa is the faculty object that needs to be removed, if yes then skip

						if (fa.getTAs() == null) {
							fa.setTAs(new ArrayList<>());
						}

						if (fa.getTAs().size() > 5) {
							continue;
						}

						if (fa.getTAs().size() < 5) {
							fa.getTAs().add(g);
							g.setAdvisor(fa);
							break;
						} // Reassign the TAs to the next faculty available before removing the faculty in
							// the parameter
					}
				}

			}

			if (f1 != null && f1.getAdvisingUgrads() != null && f1.getAdvisingUgrads().size() > 0) {
				for (UGrad g : f1.getAdvisingUgrads()) {
					for (Faculty fa : listOfFac) {
						if (fa.equals(f1)) {
							continue;
						} // check if fa is the faculty object that needs to be removed, if yes then skip

						if (fa.getAdvisingUgrads() == null) {
							fa.setAdvisingUgrads(new ArrayList<>());
						}

						if (fa.getAdvisingUgrads().size() > 8) {
							continue;
						}

						if (fa.getAdvisingUgrads().size() < 8 && !fa.getAdvisingUgrads().contains(f1)) {
							fa.getAdvisingUgrads().add(g);
							g.setAdvisor(fa);
							listOfFac.remove(f1);
							break;
						} // Reassign the underGrad student of the faculty that needs to be removed to the
							// next faculty availble
					}
				}

			}

			listOfFac.remove(f1);

			for (ProgramDirector p : pdir) {
				p.getFaculty().remove(f1);
				if (p.getFaculty().isEmpty() || p.getNumOfFaculty() == 0) {
					throw new NoSpecialtyException("There is no faculty members");
				}
			} // if a program director does not have a faculty member under supervision ,
				// throw exception

		}

	}// end class

	/**
	 * This class represents Academics
	 * 
	 * @author anhvu
	 *
	 */
	class Academics extends CSD {// start class
		protected double salary;

		/**
		 * this is the default constructor
		 */
		public Academics() {

		}

		/**
		 * this is the setSalaray method
		 * 
		 * @param d
		 */
		void setSalary(double d) {

		}

		/**
		 * this is the toString method
		 */
		public String toString() {
			return "";
		}
	}// end class

	/**
	 * This class represents the Administrator of the University
	 * 
	 * @author anhvu
	 *
	 */
	class Administrator extends Academics {// start class
		/**
		 * This is the toString method
		 */
		public String toString() {
			return "";
		}

	}// end class

	/**
	 * This class represents ProgramDirector This class contains a list of faculty
	 * members (as a record) and it contains the chairpersons it has been assigned
	 * to.
	 * 
	 * @author anhvu
	 *
	 */
	class ProgramDirector extends Administrator {// start class
		protected String program;
		protected int employee;
		ChairPerson chief;
		List<Faculty> ff;

		/**
		 * This is the default constructor
		 */
		public ProgramDirector() {
			employee = EmployeeId++;
			
		}

		/**
		 * This is the overloaded constructor for this class
		 * 
		 * @param FirstName
		 * @param LastName
		 * @param age
		 * @param gender
		 * @param address
		 */
		public ProgramDirector(String FirstName, String LastName, int age, String gender, String address) {
			// TODO Auto-generated constructor stub
			ff = new ArrayList<Faculty>();
			employee = EmployeeId++;
		}

		/**
		 * This is the setter method for program
		 * 
		 * @param string
		 */
		public void setProgram(String string) {
			// TODO Auto-generated method stub
			program = string;
		}

		/**
		 * This is the getter method for list of faculty
		 */
		public List<Faculty> getFaculty() {
			return ff;
		}

		/**
		 * This is the setter method for list of faculty
		 * 
		 * @param p
		 */
		public void setFaculty(List<Faculty> p) {
			ff = p;
		}

		/**
		 * This is the getter method for ChairPerson
		 */
		public ChairPerson getChairPerson() {
			return chief;
		}

		/**
		 * This is the setter method for ChairPerson
		 * 
		 * @param c1
		 */
		public void setChairPerson(ChairPerson c1) {
			this.chief = c1;
		}

		@Override
		public String toString() {
			return "Program Director " + program + "[[" + employee + ", " + salary + "[" + FirstName + ", " + LastName
					+ ", " + age + ", " + gender + ", " + address + "]]]";
		}

	}// end class

	/**
	 * This class implements the chairperson of the university This class contains a
	 * list of program director (as a record)
	 * 
	 * @author anhvu
	 *
	 */
	class ChairPerson extends Administrator {// start class
		protected int employee;
		protected int numberOfCP = 0;
		List<ProgramDirector> manage;

		/**
		 * This is the default constructor of this class
		 */
		public ChairPerson() {

			employee = EmployeeId++;
		}

		/**
		 * This is the overloaded constructor for this class
		 * 
		 * @param FirstName
		 * @param LastName
		 * @param age
		 * @param gender
		 * @param address
		 */
		public ChairPerson(String FirstName, String LastName, int age, String gender, String address) {
			employee = EmployeeId++;
			this.FirstName = FirstName;
			this.LastName = LastName;
			this.age = age;
			this.address = address;
			this.gender = gender;
			numberOfCP++;
			manage = new ArrayList<ProgramDirector>();
			chairma++;
		}

		/**
		 * This is the getter method for EmployeeId
		 * 
		 * @return
		 */
		public Integer getEmployeeID() {
			// TODO Auto-generated method stub
			return employee;
		}

		/**
		 * This is the setter method for salary
		 */
		@Override
		public void setSalary(double d) {
			// TODO Auto-generated method stub
			salary = d;
		}

		/**
		 * This is the getter method for salary
		 * 
		 * @return
		 */
		public double getSalary() {
			return salary;
		}

		/**
		 * This is the getter method for list of ProgramDirector
		 * 
		 * @return
		 */
		public List<ProgramDirector> getProgramDirector() {
			// TODO Auto-generated method stub

			return this.manage;
		}

		/**
		 * This is the setter method for list of ProgramDirector
		 * 
		 * @param p
		 */
		public void setProgramDirector(List<ProgramDirector> p) {
			// TODO Auto-generated method stub
			this.manage = new ArrayList<ProgramDirector>();
			for (int i = 0; i < p.size(); i++) {
				this.manage.add(p.get(i));
			}
		}

		/**
		 * 
		 */
		@Override
		public String toString() {
			return "Chair Person " + "[[[" + employee + ", " + salary + "[" + FirstName + ", " + LastName + ", " + age
					+ ", " + gender + ", " + address + "]]]]";
		}
	}// end class

	/**
	 * This class implements faculty members of the university This class contains a
	 * list of undergrad students as advising students and a list of grad students
	 * as TAs It also has the program director it has been assigned to.
	 * 
	 * @author anhvu
	 *
	 */
	class Faculty extends Academics implements Comparable {// start class
		protected String program;

		protected int employee;
		ProgramDirector dir;
		List<UGrad> AdvisingStudent;
		List<Grad> gradTa;

		/**
		 * This is the default constructor for this clas
		 */
		public Faculty() {
			employee = EmployeeId++;

		}

		/**
		 * This is the overload constructor for this class
		 * 
		 * @param FirstName
		 * @param LastName
		 * @param age
		 * @param gender
		 * @param address
		 */
		public Faculty(String FirstName, String LastName, int age, String gender, String address) {
			employee = EmployeeId++;

			this.FirstName = FirstName;
			this.LastName = LastName;
			this.age = age;
			this.address = address;
			this.gender = gender;
			AdvisingStudent = new ArrayList<UGrad>();
			gradTa = new ArrayList<Grad>();
		}

		/**
		 * This is the toString method
		 */
		public String toString() {
			return "Faculty " + program + "[[" + employee + ", " + salary + "[" + FirstName + ", " + LastName + ", " + age
					+ ", " + gender + ", " + address + "]]]";
		}

		/**
		 * This is the compareTo method
		 */
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * This is the setter method for program
		 * 
		 * @param string
		 */
		public void setProgram(String string) {
			// TODO Auto-generated method stub
			program = string;
		}

		/**
		 * This is the getter method for program
		 * 
		 * @return
		 */
		public String getProgram() {
			// TODO Auto-generated method stub
			return program;
		}

		/**
		 * This is the getter method for EmployeeId
		 * 
		 * @return an int represents EmployeeId
		 */
		public int getEmployeeID() {
			// TODO Auto-generated method stub
			return employee;
		}

		/**
		 * This is the setter method for salary
		 */
		@Override
		public void setSalary(double d) {
			// TODO Auto-generated method stub
			salary = d;
		}

		/**
		 * This is the getter method for salary
		 * 
		 * @return
		 */
		public double getSalary() {
			return salary;
		}

		/**
		 * This is the getter method for list of UGRad representing list of Advising
		 * Student
		 * 
		 * @return a list of UGrad
		 */
		public List<UGrad> getAdvisingUgrads() {
			// TODO Auto-generated method stub

			return this.AdvisingStudent;
		}

		/**
		 * This is the setter method for list of UGrad
		 * 
		 * @param List<UGrad> s
		 */
		public void setAdvisingUgrads(List<UGrad> s) {
			// TODO Auto-generated method stub
			this.AdvisingStudent = new ArrayList<UGrad>();
			for (int i = 0; i < s.size(); i++) {
				this.AdvisingStudent.add(s.get(i));
			}
		}

		/**
		 * This is the getter method for list of Grad represeting list of TAs
		 * 
		 * @return a list of Grad
		 */
		public List<Grad> getTAs() {
			// TODO Auto-generated method stub
			return this.gradTa;
		}

		/**
		 * This is the setter method for list of Grad
		 * 
		 * @param s
		 */
		public void setTAs(List<Grad> s) {
			// TODO Auto-generated method stub
			this.gradTa = new ArrayList<Grad>();
			for (int i = 0; i < s.size(); i++) {
				this.gradTa.add(s.get(i));
			}
		}

		/**
		 * This is the getter method for number of TAs
		 * 
		 * @return an int represents number of TAs
		 */
		public int getNumOfTAs() {
			// TODO Auto-generated method stub
			return gradTa.size();
		}

		/**
		 * This is the getter method for number of Advising Students
		 * 
		 * @return an int represents number of Advising Students
		 */
		public int getNumOfAdvisingUGrads() {
			// TODO Auto-generated method stub
			return AdvisingStudent.size();
		}

		/**
		 * This is the getter method for ProgramDirector
		 * 
		 * @return a ProgramDirector object
		 */
		public ProgramDirector getProgramDirector() {
			// TODO Auto-generated method stub

			return this.dir;
		}

		/**
		 * This is the setter for ProgramDirector
		 * 
		 * @param ProgramDirector s
		 */
		public void setProgramDirector(ProgramDirector s) {
			this.dir = s;
		}

	}// end class

	/**
	 * This class implements student of the university
	 * 
	 * @author anhvu
	 *
	 */
	class Student extends CSD implements Comparable {// class student

		/**
		 * This is the default constructor for this class
		 */
		public Student() {
			// studentId++;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

	}// end class

	/**
	 * This class represents undergraduate students and it has a faculty object as
	 * an advisor that it has been assigned to
	 * 
	 * @author anhvu
	 *
	 */
	class UGrad extends Student {// start class
		protected int student;
		Faculty advisor;

		/**
		 * This is the default constructor for this class
		 */
		public UGrad() {
			student = studentId++;
			UGradNum++;
			// advisor=list.get(0);
		}

		/**
		 * This is the overloaded constructor for this class
		 * 
		 * @param FirstName
		 * @param LastName
		 * @param age
		 * @param gender
		 * @param address
		 */
		public UGrad(String FirstName, String LastName, int age, String gender, String address) {
			student = studentId++;
			UGradNum++;
			this.FirstName = FirstName;
			this.LastName = LastName;
			this.age = age;
			this.address = address;
			this.gender = gender;

		}

		/**
		 * This is the getter method for advisor of the Undergraduate student
		 * 
		 * @return advisor (Faculty object)
		 */
		public Faculty getAdvisor() {
			// TODO Auto-generated method stub

			return advisor;
		}

		/**
		 * This is the setter method for advisor(Faculty object)
		 * 
		 * @param s (Faculty object)
		 */
		public void setAdvisor(Faculty s) {
			this.advisor = s;
		}

		/**
		 * This is the toString method
		 * 
		 * @return a string represents the data of UGrad
		 */
		public String toString() {
			return "Undergraduate " + "[" + student + "[[" + FirstName + ", " + LastName + ", " + age + ", " + gender + ", "
					+ address + "]]]";
		}
	}// end class

	/**
	 * This class represents grad students and it has a faculty object as an advisor
	 * that it has been assigned to
	 * 
	 * @author anhvu
	 *
	 */
	class Grad extends Student {// start class
		protected int student;
		Faculty advisor;

		/**
		 * This is the default constructor for this class
		 */
		public Grad() {
			student = studentId++;
			GradNum++;
		}

		/**
		 * This is the overloaded constructor for this class
		 * 
		 * @param FirstName
		 * @param LastName
		 * @param age
		 * @param gender
		 * @param address
		 */
		public Grad(String FirstName, String LastName, int age, String gender, String address) {
			student = studentId++;
			GradNum++;
			this.FirstName = FirstName;
			this.LastName = LastName;
			this.age = age;
			this.address = address;
			this.gender = gender;
		}

		/**
		 * This is the toString method
		 * 
		 * @return a string represents the data of Grad
		 */
		public String toString() {
			return "Graduate " + "[" + student + "[[" + FirstName + ", " + LastName + ", " + age + ", " + gender + ", "
					+ address + "]]]";
		}

		/**
		 * This is the getter method of advisor(Faculty object)
		 * 
		 * @return advisor(Faculty object)
		 */
		public Faculty getAdvisor() {
			// TODO Auto-generated method stub
			return advisor;
		}

		/**
		 * This is the setter method of advisor (Faculty object)
		 * 
		 * @param s (Faculty object)
		 */
		public void setAdvisor(Faculty s) {
			advisor = s;
		}

		/**
		 * This is the equals method. it returns true if it is the same as the object in
		 * the parameter. Otherwise, it returns false
		 */
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || obj != this) {
				return false;
			}
			Grad ob = (Grad) obj;
			return (ob.FirstName.equals(this.FirstName));

		}
	}// end class

	/**
	 * This class is a user defined Exception used when the university exceeds limit
	 * of members
	 * 
	 * @author anhvu
	 *
	 */
	class NoSpaceException extends Exception {
		public NoSpaceException() {
			super();
		}

		public NoSpaceException(String msg) {
			super(msg);
		}
	}

	/**
	 * This class is a user define Exception when a TA is graduating from the
	 * department but no other TA is available to work with a faculty
	 * 
	 * @author anhvu
	 *
	 */
	class NoTAException extends Exception {
		public NoTAException() {
			super();
		}

		public NoTAException(String msg) {
			super(msg);
		}
	}

	/**
	 * This class is a user defined Exception when a faculty member is retiring from
	 * the university but no other faculty is available with the same specialty of
	 * program.
	 * 
	 * @author anhvu
	 *
	 */
	class NoSpecialtyException extends Exception {
		public NoSpecialtyException() {
			super();
		}

		public NoSpecialtyException(String msg) {
			super(msg);
		}
	}


