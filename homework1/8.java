public class Solution {
    public int myAtoi(String str) {
        
      
		int length=str.length();
		//int newstr=0;����1��������int�ʹ洢���
		long newstr=0;
		int result=0;
		
		int i,j;
		int flag1=0;
		int sign=0;
		
		
		long min=Integer.MIN_VALUE;
		long max=Integer.MAX_VALUE;
		//System.out.println(min*(-1));
		//����2��int����ʱ��max��min��max+1�������������|max+1|;min*(-1)�����
		 
		
		
		for(i=0;i<length-1;i++)	
		{  
		    if(str.charAt(i)==' ')  continue;                                //�����ո�ʱ����������
		    
		    
		    else if(str.charAt(i)=='-'||str.charAt(i)=='+')
			{   if(str.charAt(i)=='-') sign=1;
		    	if(str.charAt(i+1)>='0'&&str.charAt(i+1)<='9')      //��+ -�ŵ�������������ֱ��Ϊ����
			     { flag1=i+1;break;}
			    else 
			    {   result=(int)newstr;
			        return result; }}
			
		    
			else if(str.charAt(i)>='0'&&str.charAt(i)<='9')                  //�޷��ŵ������ֱ�ӱ��
				{
				    flag1=i;break;
				}
			else
			{
			        result=(int)newstr;
			        return result;
			        
			 }
		}
		
		
		
		if(i>=length) {result=(int)newstr;return result;}          //�ж�String����û�����ֻ�+ -�ţ����û�з���0
		else
		 {
			for(j=flag1;j<length;j++)                                     //����У��Ͱ��ַ���ת��Ϊ��������
		    {  			
			  if(str.charAt(j)>='0'&&str.charAt(j)<='9')
			  {
				  newstr=newstr*10+(str.charAt(j)-'0');
				
				  
				   
				  if((newstr>max)&&(sign==0)){newstr=max;break;}
				  else if(newstr>(max+1)&&sign==1){newstr=min*(-1);break;}  //�������ֵ�ķ�Χ
	    	  }
			  else break;
		    }
		    
			if(sign==1)newstr=newstr*(-1);                               //���֮ǰ�ķ����ǡ�-����Ӧ�����Ϊ��
			{result=(int)newstr;return result;}
			
		}
    }
}