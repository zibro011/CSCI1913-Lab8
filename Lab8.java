class RunnyStack<Base> 
{
  private class Run
  {
    final Base base;
    private int length;
    private Run next;
    
    public Run (Base base)  //represents a run of bases
    {
      this.base = base;
      length = 1;
      next = null;
    }
    
    public Run (Base base, Run next)
    {
      this.base = base;
      this.next = next;
      length = 1;
    }
  }
  
  private Run top;
  private int depth, run;
  
  public RunnyStack() //constructor
  {
    top = null;
    depth = run = 0;
  }
  public int depth() //returns the sum of the lengths of all the runs in the stack
  {
    return depth;
  }
  public boolean isEmpty() //test for empty stack
  {
    return top == null;
  }
  public Base peek() //returns the base at the top of the stack
  {
    if(isEmpty())
    {
      throw new IllegalStateException("Stack is empty");
    }
    else
    {
      return top.base;
    }
  }
  public void pop()
  {
    if(isEmpty())
    {
      throw new IllegalStateException("Stack is empty");
    }
    else
    {
      top.length--; //decrement the length of the run
      depth--;
      if (top.length == 0)
      {
        top = top.next;//remove this run
        run--;
      }
    }
  }
  public void push(Base base)
  {
    if(isEmpty())
    {
      top = new Run(base); //add new run of one base to the top
      run++;
    }
    else
    {
      if (top.base.equals(base)) //test if base is equal to the object at the top 
      {
        top.length++;
      }
      else
      {
        top = new Run(base, top);
        run++;
      }
    }
    depth++;
  }
  public int runs() //returns the number of runs in the stack
  {
    return run;
  }
  
}

//
//  Tests for CSci 1913 Lab 8
//  James Moen
//  20 Mar 17
//
//  The TRY-CATCH statements catch exceptions thrown by RUNNY STACK's methods,
//  so that the program can continue to run even if a method fails. We still
//  haven't talked about TRY-CATCH'es in the lectures yet.
//
//  Most tests have comments that show what they should print, and how many
//  points they are worth, for a total of 40 points.
//
//  Camembert is a soft French cheese. It may be runny. It can be stacked.
//

class Camembert
{
  public static void main(String [] args)
  {
    RunnyStack<String> s = new RunnyStack<String>();

    System.out.println(s.isEmpty());         //  true       1 point
    System.out.println(s.depth());           //  0          1 point
    System.out.println(s.runs());            //  0          1 point

    try
    {
      s.pop();
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No pop");          //  No pop     1 point
    }

    try
    {
      System.out.println(s.peek());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No peek");         //  No peek    1 point
    }

    s.push("A");
    System.out.println(s.peek());            //  A          1 point
    System.out.println(s.depth());           //  1          1 point
    System.out.println(s.runs());            //  1          1 point

    System.out.println(s.isEmpty());         //  false      1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  2          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  3          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  4          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("C");
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  5          1 point
    System.out.println(s.runs());            //  3          1 point

    s.push("C");
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  6          1 point
    System.out.println(s.runs());            //  3          1 point

    s.pop();
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  5          1 point
    System.out.println(s.runs());            //  3          1 point

    s.pop();
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  4          1 point
    System.out.println(s.runs());            //  2          1 point

    s.pop();
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  3          1 point
    System.out.println(s.runs());            //  2          1 point

    s.pop();
    s.pop();
    System.out.println(s.peek());            //  A          1 point
    System.out.println(s.depth());           //  1          1 point
    System.out.println(s.runs());            //  1          1 point

    s.pop();
    System.out.println(s.isEmpty());         //  true       1 point
    System.out.println(s.depth());           //  0          1 point
    System.out.println(s.runs());            //  0          1 point

    try
    {
      System.out.println(s.peek());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No peek");         //  No peek    1 point
    }
  }
}