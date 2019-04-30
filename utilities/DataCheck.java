package utilities;

import javax.servlet.http.HttpServletRequest;

public class DataCheck {

		public static boolean NullOrEmpty(String input)
	    {
	        if(input==null||input.trim().equals(""))    
	        {
	            return true;                          
	        }
	        else
	        {
	            return false;                          
	        }
	    }
		
	   public static boolean IfReferredBy(HttpServletRequest request, String cameFrom)
	    {
	        if(request.getHeader("Referer")==null)
	        {
	            return false;
	        }
	        else
	        {
	            String Referer = (String)request.getHeader("Referer");
	            if(Referer.contains(cameFrom))
	            {
	                return true;
	            }
	            else
	            {
	                return false;
	            }
	        }
	    }
	


}
