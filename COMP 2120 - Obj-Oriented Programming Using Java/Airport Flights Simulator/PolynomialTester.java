/**
   A class to to test the Polynomial class.
*/
public class PolynomialTester
{
   public static void main(String[] args)
   {
      System.out.println("Creating P1(x) with (-10,0)...");
      Polynomial p1 = new Polynomial(-10,0);
      System.out.println("P1(x) = " + p1);

      System.out.println("Adding (-1,1) to P1(x)...");
      p1.add(new Polynomial(-1,1));
      System.out.println("P1(x) = " + p1);

      System.out.println("Adding (9,7) to P1(x)...");
      p1.add(new Polynomial(9,7));
      System.out.println("P1(x) = " + p1);

      System.out.println("Adding (5,1) to P1(x)...");
      p1.add(new Polynomial(5,1));
      System.out.println("P1(x) = " + p1);

      System.out.println("Creating P2(x) with (3,-2)...");
      Polynomial p2 = new Polynomial(3,-2);
      System.out.println("P2(x) = " + p2);

      System.out.println("Creating P2(x) with (3,2)...");
      p2 = new Polynomial(3,2);
      System.out.println("P2(x) = " + p2);

      System.out.println("Adding (2,1) to P2(x)...");
      p2.add(new Polynomial(2,1));
      System.out.println("P2(x) = " + p2);

      System.out.println("Adding (2,0) to P2(x)...");
      p2.add(new Polynomial(2,0));
      System.out.println("P2(x) = " + p2);

      System.out.println("Adding P2(x) to P1(x)...");
      p1.add(p2);
      System.out.println("P1(x) = P1(x) + P2(x) = " + p1);

      System.out.println("Subtracting P2(x) from P1(x)...");
      p1.subtract(p2);
      System.out.println("P1(x) = P1(x) - P2(x) = " + p1);

      System.out.println("Creating Q(x) with multiplying P1(x) by P2(x)...");
      Polynomial q = p1.multiply(p2);
      System.out.println("Q(x) = P1(x) * P2(x) = " + q);

      System.out.println("Degree of P1(x) = " + p1.getDegree());

      System.out.println("Coefficient of x in P1(x) = " + p1.coefficient(1));

      System.out.println("P2(3) = " + p2.evaluate(3));

      System.out.println("P1(x) = " + p1);
      System.out.println("P2(x) = " + p2);
      System.out.println("P1(x) " + (p1.equals(p2)?"is equal to P2(x)":"is not equal to P2(x)"));

      System.out.println("Creating P3(x) with (3,2), adding (2,1), and adding (2,0)...");
      Polynomial p3 = new Polynomial(3,2);
      p3.add(new Polynomial(2,1));
      p3.add(new Polynomial(2,0));
      System.out.println("P3(x) = " + p3);
      System.out.println("P2(x) = " + p2);
      System.out.println("P3(x) " + (p2.equals(p3)?"is equal to P2(x)":"is not equal to P2(x)"));
   }
}
