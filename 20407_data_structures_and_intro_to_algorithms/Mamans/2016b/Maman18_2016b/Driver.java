

public class Driver 
{
	public static void main(String []args)  
	{
		
		DB one = new DB();
		
		
		//InputStream f = new FileInputStream("text.txt");
		
		//adding 14 books.
		one.addBook("AX1270");
		one.addBook("AX1271");
		one.addBook("AX1272");
		one.addBook("AX1273");
		one.addBook("AX1274");
		one.addBook("AX1275");
		one.addBook("AX1276");
		one.addBook("AX1277");
		one.addBook("AX1279");
		one.addBook("AX1278");
		one.addBook("AX1280");
		one.addBook("AX1281");
		one.addBook("AX1260");
		one.addBook("AX1240");
		//14 books added.
		
		
		
		
		//-------------------------------------------------------------------------------
		
		//first input - (include 65 input lines) 
		/*
		one.dbDriver("+ Asis 026503151");
		one.dbDriver("+ Cohen 026553151");
		one.dbDriver("+ Lapid 096503151");
		one.dbDriver("+ Sharon 026573151");
		one.dbDriver("+ Bush  026507151");
		one.dbDriver("+ Baraq 026803151");
		one.dbDriver("+ Trump 026603151");
		one.dbDriver("+ Clinton 036804151");
		one.dbDriver("+ Levy 016803151");
		one.dbDriver("+ GoldMember 026803999");
		one.dbDriver("+ Powers 026893151");
		//11 members added.
		
		//one.printMemberTree();
		
		one.dbDriver("- Asis 026503151");
		one.dbDriver("+ Cohen 026553151");
		//one deleting member and one time try to add a exist member.
		
		one.dbDriver("   Asis        026503151       AX1270       +   ");//not-exist member
		one.dbDriver("   Clinton        036804151       AX1271       +   ");//borrow 1 book.
		one.dbDriver("   Trumo        026603151       AX1272       +   ");//unmatched
		one.dbDriver("   Trump        026603151       AX1273       +   ");//borrow 1 book.
		one.dbDriver("   Baraq        026803151       AX1274       +   ");//1
		one.dbDriver("   Baraq        026803151       AX1275       +   ");//2
		one.dbDriver("   Baraq        026803151       AX1276       +   ");//3
		one.dbDriver("   Baraq        026803151       AX1277       +   ");//4
		one.dbDriver("   Baraq        026803151       AX1278       +   ");//5
		one.dbDriver("   Baraq        026803151       AX1279       +   ");//6
		one.dbDriver("   Baraq        026803151       AX1280       +   ");//7
		one.dbDriver("   Baraq        026803151       AX1281       +   ");//8
		one.dbDriver("   Baraq        026803151       AX1272       +   ");//9
		one.dbDriver("   Baraq        026803151       AX1240       +   ");//10
		one.dbDriver("   Baraq        026803151       AX1260       +   ");//11
		//15 borrowed books. Baraq borrow 11 - to check for bounds error.
		//one time to a unmatched id and name.
		//one time to not-exist member.
		//twice to different members.
		
		one.dbDriver("?AX1274");
		one.dbDriver("?026803151");
		one.dbDriver("?036804151");
		one.dbDriver("?AX1280");
		one.dbDriver("?!");
		//5 Queries.
		
		one.dbDriver("   Baraq        026803151       AX1274       -   ");//1
		one.dbDriver("   Baraq        026803151       AX1275          -");//2
		one.dbDriver("   Baraq        026803151       AX1276 -         ");//3
		one.dbDriver("   Baraq        026803151       AX1277     -     ");//4
		one.dbDriver("   Baraq        026803151       AX1278       -   ");//5
		one.dbDriver("   Baraq        026803151       AX1279       -   ");//6
		one.dbDriver("   Baraq        026803151       AX1280       -   ");//7
		one.dbDriver("   Baraq        026803151       AX1281       -   ");//8
		one.dbDriver("   Baraq        026803151       AX1272       -   ");//9
		one.dbDriver("   Baraq        026803151       AX1240       -   ");//10
		one.dbDriver("   Baraq        026803151       AX1260       -   ");//11
		//Baraq return all the books. including one he never borrow .check for bounds error.
		//11 lines.
		
		one.dbDriver("?AX1276");
		one.dbDriver("?026803151");
		one.dbDriver("?036804151");
		one.dbDriver("?AX1280");
		one.dbDriver("?!");
		//5 Queries.
		
		one.dbDriver("   Baraq        026803151       AX1274       +   ");//1
		one.dbDriver("   Baraq        026803151       AX1275       +   ");//2
		one.dbDriver("   Baraq        026803151       AX1276       +   ");//3
		one.dbDriver("   Baraq        026803151       AX1277       +   ");//4
		one.dbDriver("   Baraq        026803151       AX1278       +   ");//5
		one.dbDriver("   Baraq        026803151       AX1279       +   ");//6
		one.dbDriver("   Baraq        026803151       AX1280       +   ");//7
		one.dbDriver("   Baraq        026803151       AX1281       +   ");//8
		one.dbDriver("   Baraq        026803151       AX1272       +   ");//9
		one.dbDriver("   Baraq        026803151       AX1240       +   ");//10
		one.dbDriver("   Baraq        026803151       AX1260       +   ");//11
		//Baraq borrow 11 - to check for bounds error.second time. one borrowed by Trump/Clinton already.
		//11 lines.
		
		one.dbDriver("?AX1276");
		one.dbDriver("?026803151");
		one.dbDriver("?036804151");
		one.dbDriver("?AX1280");
		one.dbDriver("?!");
		//5 Queries.
		
		*/
		
		
		//--------------------------------------------------------------------------------------------------------------
		//Second input -
		//try to stress the members tree.
		//40 lines.
		
		one.printMemberTree();
		
		one.dbDriver("+ Asis 026503151");
		one.dbDriver("+ Cohen 026553151");
		one.dbDriver("+ Lapid 096503151");
		one.dbDriver("+ Sharon 026573151");
		one.dbDriver("+ Bush  026507151");
		one.dbDriver("+ Baraq 026803151");
		one.dbDriver("+ Trump 026603151");
		one.dbDriver("+ Clinton 036804151");
		one.dbDriver("+ Levy 016803151");//here.
		//9 members.
		one.printMemberTree();
		
		one.dbDriver("+ Asis 016503151");
		one.dbDriver("+ Cohen 016553151");
		one.dbDriver("+ Lapid 016563151");
		one.dbDriver("+ Sharon 016573151");
		one.dbDriver("+ Bush  016507151");
		one.dbDriver("+ Baraq 016803151");//The same id as Levy, in the perveous parse.^
		one.dbDriver("+ Trump 016603151");
		one.dbDriver("+ Clinton 016804151");
		one.dbDriver("+ Levy 016811151");
		//9 members. one with an exist id.
		one.printMemberTree();
		
		one.dbDriver("+ Asis 026593151");//here.
		one.dbDriver("+ Cohen 026593151");//The same id as Asis, in this parse.the first member.
		one.dbDriver("+ Lapid 096593151");
		one.dbDriver("+ Sharon 026593151");//The same id as Asis, in this parse.the first member.
		one.dbDriver("+ Bush  026597151");
		one.dbDriver("+ Baraq 026893151");
		one.dbDriver("+ Trump 026693151");
		one.dbDriver("+ Clinton 036894151");
		one.dbDriver("+ Levy 016893151");
		//9 members. two with an exist id.
		one.printMemberTree();
		
		one.dbDriver("?AX1276");
		one.dbDriver("?026803151");
		one.dbDriver("?036804151");
		one.dbDriver("?AX1280");
		one.dbDriver("?!");
		//5 Queries.
		
		
		one.dbDriver("   Baraq        026803151       AX1274       +   ");//1
		one.dbDriver("Baraq 026893151       AX1275       +   ");//2
		one.dbDriver("  	 Trump 026693151     	  AX1276  	     +");//3
		one.dbDriver("   Clinton 036894151       AX1277       +   ");//4
		//4 borrowed books. test input line borders and array _list .
		
		one.dbDriver("?AX1276");
		one.dbDriver("?026803151");
		one.dbDriver("?036804151");
		one.dbDriver("?AX1280");
		one.dbDriver("?!");
		//5 Queries.
		
		
		
		System.out.println("Driver:OUT!.\n");//Symbol quieting from the data base.
		
		
	}//End of main().
	
}
