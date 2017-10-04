import java.util.*;

public class assn1q3 {
	static Boolean is_ok=false;
	static List<List<List<String>>> list=new ArrayList<List<List<String>>>();

	public static void main(String[] args)
	{
		List<String> left = new ArrayList<String>(); 
		List<String> right = new ArrayList<String>();
		String input1="";
		for (int x=0; x<args.length; x++)
		{
			if(x==0)
				input1+=args[x];
			else
				input1+=" "+args[x];
		}
		//String input1 = "[(neg (p or q))] seq [(neg p)]";
		//input1=input1.substring(1, input1.length()-1);
		String[] input2 = input1.split(" seq ");
		if(input2[0].equals("[]"))
		{
			input2[1]=input2[1].substring(1, input2[1].length()-1);
			String[] input21=input2[1].split(", ");
			for(int b=0; b<input21.length; b++)
			{
				right.add(input21[b]);
			}
		}
		else
		{
			input2[0]=input2[0].substring(1, input2[0].length()-1);
			input2[1]=input2[1].substring(1, input2[1].length()-1);
			String[] input11=input2[0].split(", ");
			String[] input21=input2[1].split(", ");
			
			for(int a=0; a<input11.length; a++)
			{
				left.add(input11[a]);
			}
			for(int b=0; b<input21.length; b++)
			{
				right.add(input21[b]);
			}
		}
		
		//System.out.println(left.get(0));
		if (Prove(left,right))
		{   
			int num=0;
			System.out.println("true");
			for (int c=list.size()-1; c>=0; c--)
			{
				num+=1;
				if (list.get(c).get(0).size() != 0 && list.get(c).get(1).size() != 0)
				{
					if (list.get(c).get(2).get(0).equals("Rule 1"))
						System.out.print(num+"  "+list.get(c).get(2)+"      [");
					else
						System.out.print(num+"  "+list.get(c).get(2)+"    [");
					for (int d=0; d<list.get(c).get(0).size(); d++)
					{
						if(d != list.get(c).get(0).size()-1)
							System.out.print(list.get(c).get(0).get(d)+", ");
						else
							System.out.print(list.get(c).get(0).get(d)+"]");
					}
					System.out.print(" seq [");
					for (int e=0; e<list.get(c).get(1).size(); e++)
					{
						if(e != list.get(c).get(1).size()-1)
							System.out.print(list.get(c).get(1).get(e)+", ");
						else
							System.out.print(list.get(c).get(1).get(e)+"]\n");
					}
				}
				else 
				{
					if(list.get(c).get(1).size() == 0)
					{
						if (list.get(c).get(2).get(0).equals("Rule 1"))
							System.out.print(num+"  "+list.get(c).get(2)+"      [");
						else
							System.out.print(num+"  "+list.get(c).get(2)+"    [");
						for (int g=0; g<list.get(c).get(0).size(); g++)
						{
							if(g != list.get(c).get(0).size()-1)
								System.out.print(list.get(c).get(0).get(g)+", ");
							else
								System.out.print(list.get(c).get(0).get(g)+"]");
						}
						System.out.print(" seq []\n");
					}
					else
					{
						if (list.get(c).get(2).get(0).equals("Rule 1"))
							System.out.print(num+"  "+list.get(c).get(2)+"      [] seq [");
						else
							System.out.print(num+"  "+list.get(c).get(2)+"    [] seq [");
						for (int f=0; f<list.get(c).get(1).size(); f++)
						{
							if(f != list.get(c).get(1).size()-1)
								System.out.print(list.get(c).get(1).get(f)+", ");
							else
								System.out.print(list.get(c).get(1).get(f)+"]\n");
						}
					}
				}
			}
			System.out.println("QED.");
		}
		else
			System.out.println("false");
	}
	
	
	public static boolean Prove(List<String> left, List<String> right)
	{	
		List<List<String>> each_list=new ArrayList<List<String>>();
			if (is_1(left, right))
			{
				List<String> rule = new ArrayList<String>();
				rule.add("Rule 1");
				each_list.add(left);
				each_list.add(right);
				each_list.add(rule);
				list.add(each_list);
				return true;
			}
			else 
			{
				if (is_P2a(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p2a = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P2a");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p2a=do_P2a(left, right);
					if (Prove(p2a.get(1), p2a.get(2)))
						return true;
					else
						list=former;
				}
				if (is_P2b(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p2b = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P2b");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p2b=do_P2b(left, right);
					if (Prove(p2b.get(1), p2b.get(2)))
						return true;
					else
						list=former;
				}
				if(is_P3a(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p3a = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P3a");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p3a=do_P3a(left, right);
					if (Prove(p3a.get(1), p3a.get(2)) && Prove(p3a.get(3), p3a.get(4)))
						return true;
					else
						list=former;
				}
				if(is_P3b(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p3b = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P3b");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p3b=do_P3b(left, right);
					if (Prove(p3b.get(1), p3b.get(2)))
						return true;
					else
						list=former;
				}
				if(is_P4a(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p4a = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P4a");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p4a=do_P4a(left, right);
					if (Prove(p4a.get(1), p4a.get(2)))
						return true;
					else
						list=former;
				}
				if(is_P4b(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p4b = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P4b");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p4b=do_P4b(left, right);
					if (Prove(p4b.get(1), p4b.get(2)) && Prove(p4b.get(3), p4b.get(4)))
						return true;
					else
						list=former;
				}
				if(is_P5a(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p5a = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P5a");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p5a=do_P5a(left, right);
					if (Prove(p5a.get(1), p5a.get(2)))
						return true;
					else
						list=former;
				}
				if(is_P5b(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p5b = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P5b");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p5b=do_P5b(left, right);
					if (Prove(p5b.get(1), p5b.get(2)) && Prove(p5b.get(3), p5b.get(4)))
						return true;
					else
						list=former;
				}
				if(is_P6a(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p6a = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P6a");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p6a=do_P6a(left, right);
					if (Prove(p6a.get(1), p6a.get(2)) && Prove(p6a.get(3), p6a.get(4)))
						return true;
					else
						list=former;
				}
				if(is_P6b(left, right))
				{
					List<List<List<String>>> former=new ArrayList<List<List<String>>>();
					former=list;
					Map<Integer, List<String>> p6b = new HashMap<Integer, List<String>>();
					List<String> rule = new ArrayList<String>();
					rule.add("Rule P6b");
					each_list.add(left);
					each_list.add(right);
					each_list.add(rule);
					list.add(each_list);
					p6b=do_P6b(left, right);
					if (Prove(p6b.get(1), p6b.get(2)) && Prove(p6b.get(3), p6b.get(4)))
						return true;
					else
						list=former;
				}
				
				return false;
				
			}
	}
	
	public static boolean is_1(List<String> left, List<String> right)
	{
		if(left.size()==0)
			return false;
		else
		{
			for(int i=0; i<left.size(); i++)
			{
				for(int j=0; j<right.size(); j++)
				{
					if (left.get(i).equals(right.get(j)))
					{
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public static boolean is_neg(String str)
	{
		String rest;
		int num=1;
		if(str.startsWith("(neg") && str.endsWith(")"))
		{
			rest=str.substring(5);
			for(int i=0; i<rest.length(); i++)
			{
				if (rest.substring(i, i+1).equals("("))
				{
					num+=1;
				}
				if (rest.substring(i, i+1).equals(")"))
				{
					num-=1;
					if(num==0)
					{
						if(i==rest.length()-1)
							return true;
						else
							return false; 
					}
				}
			}
			return false;
		}
		else
			return false;
	}
	
	public static boolean is_P2a(List<String> left, List<String> right)
	{
		if (right.size()==0)
			return false;
		else
		{
			for(int i=0; i<right.size(); i++)
			{
				if (is_neg(right.get(i)))
				{
					return true;
				}
			}
			return false;
		}
	}
	
	public static boolean is_P2b(List<String> left, List<String> right)
	{
		if (left.size()==0)
			return false;
		else
		{
			for(int i=0; i<left.size(); i++)
			{
				if (is_neg(left.get(i)))
				{
					return true;
				}
			}
			return false;
		}
	}
	
	// for "and", "imp", "iff", return map(boolean, index)
	public static Map<String, Integer> is_connect(String str, String connect)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		int key=0;
		int index=-1;
		int num=0;
		boolean is_find=false;
		
		if(is_neg(str))
			key=0;
		else
		{
			for (int i=0; i<str.length(); i++)
			{
				if (!is_find)
				{
					if (str.substring(i, i+1).equals("("))
					{
						is_find=true;
						if(i==0)
							num+=1;
						else
						{	index=i-4;
							break;}
					}
				}
				else
				{
					if (str.substring(i, i+1).equals("("))
						num+=1;
					if (str.substring(i, i+1).equals(")"))
					{
						num-=1;
						if(num==0)
						{
							index=i+2;
							break;
						}
					}
				}
			}
			
			if(is_find)
			{
				if(str.substring(index, index+3).equals(connect))
				{
					key=1;
				}
			}
			else
			{
				if(str.indexOf(connect)!=-1)
				{
					key=1;
					index=str.indexOf(connect);
				}
				else
					key=0;	
			}
		}
		map.put("judge", key);
		map.put("index", index);
		return map;
		
	}
	
	// for "or", return map(boolean, index)
		public static Map<String, Integer> is_or(String str)
		{
			Map<String, Integer> map = new HashMap<String, Integer>();
			int key=0;
			int index=-1;
			int num=0;
			boolean is_find=false;
			
			if(is_neg(str))
				key=0;
			else
			{
				for (int i=0; i<str.length(); i++)
				{
					if (!is_find)
					{
						if (str.substring(i, i+1).equals("("))
						{
							is_find=true;
							if(i==0)
								num+=1;
							else
							{	index=i-3;
								break;}
						}
					}
					else
					{
						if (str.substring(i, i+1).equals("("))
							num+=1;
						if (str.substring(i, i+1).equals(")"))
						{
							num-=1;
							if(num==0)
							{
								index=i+2;
								break;
							}
						}
					}
				}
				
				if(is_find)
				{
					if(str.substring(index, index+2).equals("or"))
					{
						key=1;
					}
				}
				else
				{
					if(str.indexOf("or")!=-1)
					{
						key=1;
						index=str.indexOf("or");
					}
					else
						key=0;	
				}
			}
			map.put("judge",key);
			map.put("index", index);
			return map;
			
		}
	
	public static boolean is_P3a(List<String> left, List<String> right)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if (right.size()==0)
			return false;
		else
		{
			for (int i=0; i<right.size(); i++)
			{
				map=is_connect(right.get(i), "and");
				if (map.get("judge")==1)
					return true;
			}
			return false;
		}
	}

	public static boolean is_P3b(List<String> left, List<String> right)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if(left.size()==0)
			return false;
		else
		{
			for (int i=0; i<left.size(); i++)
			{
				map=is_connect(left.get(i), "and");
				if (map.get("judge")==1)
					return true;
			}
			return false;
		}
	}

	public static boolean is_P4a(List<String> left, List<String> right)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if (right.size()==0)
			return false;
		else
		{
			for (int i=0; i<right.size(); i++)
			{
				map=is_or(right.get(i));
				if (map.get("judge")==1)
					return true;
			}
			return false;
		}
	}

	public static boolean is_P4b(List<String> left, List<String> right)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if(left.size()==0)
			return false;
		else
		{
			for (int i=0; i<left.size(); i++)
			{
				map=is_or(left.get(i));
				if (map.get("judge")==1)
					return true;
			}
			return false;
		}
	}
	
	public static boolean is_P5a(List<String> left, List<String> right)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if(right.size()==0)
			return false;
		else
		{
			for (int i=0; i<right.size(); i++)
			{
				map=is_connect(right.get(i), "imp");
				if (map.get("judge")==1)
					return true;
			}
			return false;
		}
	}
	
	public static boolean is_P5b(List<String> left, List<String> right)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if(left.size()==0)
			return false;
		else
		{
			for (int i=0; i<left.size(); i++)
			{
				map=is_connect(left.get(i), "imp");
				if (map.get("judge")==1)
					return true;
			}
			return false;
		}
	}
	
	public static boolean is_P6a(List<String> left, List<String> right)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if(right.size()==0)
			return false;
		else
		{
			for (int i=0; i<right.size(); i++)
			{
				map=is_connect(right.get(i), "iff");
				if (map.get("judge")==1)
					return true;
			}
			return false;
		}
	}
	
	public static boolean is_P6b(List<String> left, List<String> right)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if(left.size()==0)
			return false;
		else
		{
			for (int i=0; i<left.size(); i++)
			{
				map=is_connect(left.get(i), "iff");
				if (map.get("judge")==1)
					return true;
			}
			return false;
		}
	}
	
	
	public static String change(String str)
	{
		if (is_neg(str))
			return str;
		else
		{
			if(str.startsWith("(") && !str.startsWith("(neg") && str.endsWith(")"))
			{
				str=str.substring(1, str.length()-1);
				return str;
			}
			else
				return str;
		}
	}
	
	
	public static Map<Integer, List<String>> do_P2a(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change;
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
			}
		}
		
		for(int i=0; i<right.size(); i++)
		{
			if (is_neg(right.get(i)))
			{
				change=right.get(i).substring(5, right.get(i).length()-1);
				change=change(change);
				new_right.remove(i);
				new_left.add(change);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		return map;
	}

	public static Map<Integer, List<String>> do_P2b(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change;
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
			}
		}
		
		for(int i=0; i<left.size(); i++)
		{
			if (is_neg(left.get(i)))
			{
				change=left.get(i).substring(5, left.get(i).length()-1);
				change=change(change);
				new_left.remove(i);
				new_right.add(change);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		return map;
	}
	
	public static Map<Integer, List<String>> do_P3a(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change1;
		String change2;
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		List<String> left1 = new ArrayList<String>();
		List<String> right1 = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
				left1.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
				right1.add(right.get(h));
			}
		}
		
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		
		for (int i=0; i<right.size(); i++)
		{
			map1=is_connect(right.get(i), "and");
			if (map1.get("judge")==1)
			{
				change1=right.get(i).substring(0, map1.get("index")-1);
				change2=right.get(i).substring(map1.get("index")+4);
				change1=change(change1);
				change2=change(change2);
				new_right.set(i, change1);
				right1.set(i, change2);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		map.put(3, left1);
		map.put(4, right1);
		return map;
	}
	
	public static Map<Integer, List<String>> do_P3b(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change1;
		String change2;
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
			}
		}
		
		for (int i=0; i<left.size(); i++)
		{
			map1=is_connect(left.get(i), "and");
			if (map1.get("judge")==1)
			{
				change1=left.get(i).substring(0, map1.get("index")-1);
				change2=left.get(i).substring(map1.get("index")+4);
				change1=change(change1);
				change2=change(change2);
				new_left.remove(i);
				new_left.add(change1);
				new_left.add(change2);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		return map;
	}
	
	public static Map<Integer, List<String>> do_P4a(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change1;
		String change2;
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
			}
		}
		
		for (int i=0; i<right.size(); i++)
		{
			map1=is_or(right.get(i));
			if (map1.get("judge")==1)
			{
				change1=right.get(i).substring(0, map1.get("index")-1);
				change2=right.get(i).substring(map1.get("index")+3);
				change1=change(change1);
				change2=change(change2);
				new_right.remove(i);
				new_right.add(change1);
				new_right.add(change2);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		return map;
	}
	
	public static Map<Integer, List<String>> do_P4b(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change1;
		String change2;
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		List<String> left1 = new ArrayList<String>();
		List<String> right1 = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
				left1.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
				right1.add(right.get(h));
			}
		}
		
		for (int i=0; i<left.size(); i++)
		{
			map1=is_or(left.get(i));
			if (map1.get("judge")==1)
			{
				change1=left.get(i).substring(0, map1.get("index")-1);
				change2=left.get(i).substring(map1.get("index")+3);
				change1=change(change1);
				change2=change(change2);
				new_left.set(i, change1);
				left1.set(i, change2);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		map.put(3, left1);
		map.put(4, right1);
		return map;
	}
	
	public static Map<Integer, List<String>> do_P5a(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change1;
		String change2;
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
			}
		}
		
		for (int i=0; i<right.size(); i++)
		{
			map1=is_connect(right.get(i), "imp");
			if (map1.get("judge")==1)
			{
				change1=right.get(i).substring(0, map1.get("index")-1);
				change2=right.get(i).substring(map1.get("index")+4);
				change1=change(change1);
				change2=change(change2);
				new_right.set(i,change2);
				new_left.add(change1);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		return map;
	}
	
	public static Map<Integer, List<String>> do_P5b(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change1;
		String change2;
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		List<String> left1 = new ArrayList<String>();
		List<String> right1 = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
				left1.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
				right1.add(right.get(h));
			}
		}
		
		for (int i=0; i<left.size(); i++)
		{
			map1=is_connect(left.get(i), "imp");
			if (map1.get("judge")==1)
			{
				change1=left.get(i).substring(0, map1.get("index")-1);
				change2=left.get(i).substring(map1.get("index")+4);
				change1=change(change1);
				change2=change(change2);
				new_left.set(i, change2);
				left1.remove(i);
				right1.add(change1);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		map.put(3, left1);
		map.put(4, right1);
		return map;
	}
	
	public static Map<Integer, List<String>> do_P6a(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change1;
		String change2;
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		List<String> left1 = new ArrayList<String>();
		List<String> right1 = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
				left1.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
				right1.add(right.get(h));
			}
		}
		
		for (int i=0; i<right.size(); i++)
		{
			map1=is_connect(right.get(i), "iff");
			if (map1.get("judge")==1)
			{
				change1=right.get(i).substring(0, map1.get("index")-1);
				change2=right.get(i).substring(map1.get("index")+4);
				change1=change(change1);
				change2=change(change2);
				new_right.set(i, change2);
				new_left.add(change1);
				right1.set(i, change1);
				left1.add(change2);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		map.put(3, left1);
		map.put(4, right1);
		return map;
	}
	
	public static Map<Integer, List<String>> do_P6b(List<String> left, List<String> right)
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String change1;
		String change2;
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		List<String> new_left = new ArrayList<String>();
		List<String> new_right = new ArrayList<String>();
		List<String> left1 = new ArrayList<String>();
		List<String> right1 = new ArrayList<String>();
		if (!left.isEmpty())
		{
			for (int j=0; j<left.size(); j++)
			{
				new_left.add(left.get(j));
				left1.add(left.get(j));
			}
		}
		if (!right.isEmpty())
		{
			for (int h=0; h<right.size(); h++)
			{
				new_right.add(right.get(h));
				right1.add(right.get(h));
			}
		}
		
		for (int i=0; i<left.size(); i++)
		{
			map1=is_connect(left.get(i), "iff");
			if (map1.get("judge")==1)
			{
				change1=left.get(i).substring(0, map1.get("index")-1);
				change2=left.get(i).substring(map1.get("index")+4);
				change1=change(change1);
				change2=change(change2);
				new_left.remove(i);
				new_left.add(change1);
				new_left.add(change2);
				left1.remove(i);
				right1.add(change1);
				right1.add(change2);
				break;
			}
		}
		map.put(1, new_left);
		map.put(2, new_right);
		map.put(3, left1);
		map.put(4, right1);
		return map;
	}
	
	
	
}
