package com.huilian.entity;

import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class PageUtil  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 获取目标页码
     * @return
     */
    public static int getTargetPage(ServletRequest request){

        int pageNum = 1;

        String pageNumStr = request.getParameter("pageNum");

        if(!StringUtils.isEmpty(pageNumStr)){
            try{
                pageNum = Integer.valueOf(pageNumStr);
            }catch(Exception e){
                pageNum = 1;
            }
        }

        return pageNum;
    }

    /**
     * 获取每页条数
     * @return
     */
    public static int getPageSize(ServletRequest request){

        int pageSize = 10;

        String pageSizeStr = request.getParameter("pageSize");

        if(!StringUtils.isEmpty(pageSizeStr)){
            try{
                pageSize = Integer.valueOf(pageSizeStr);
            }catch(Exception e){
                pageSize = 10;
            }
        }

        return pageSize;
    }

    /**
     * 将Map中的查询条件，加上前缀，以&拼接
     * @param map
     * @param prefix 每个参数的前缀
     * @return
     */
    public static String encodeParameterStringWithPrefix(Map<String,Object> map, String prefix){

        if(null == map || map.isEmpty()){
            return "";
        }

        StringBuffer sb = new StringBuffer();

        Iterator<Map.Entry<String,Object>> entries = map.entrySet().iterator();

        while (entries.hasNext()) {

            if(!StringUtils.isEmpty(sb.toString())){
                sb.append("&");
            }

            Map.Entry<String,Object> entry = entries.next();

            sb.append(prefix);
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());

        }

        return sb.toString();

    }
}
