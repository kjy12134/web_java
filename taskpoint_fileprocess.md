backuppath도 정규식 처리 해라. 

파일경로와 파일명을 분리



public boolean FileProcess(String strInsertQueryId) throws Exception {
​        

        //정규화 체크 소스 추가
        UpdateTask task = null;
        try {
            task = new UpdateTask();
        } catch (Exception e) {
            CommonUtil.println(strTaskBatchCd, "TaskPoint FileProcess UpdateTask error " + ExceptionUtils.getFullStackTrace(e));
        }
        
        String regEx = "^/home/ifspo/data/$";
        if (XSSUtil.chkRegExp(regEx, saveDir)) { // 정규화 통과
            CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " FileProcess 정규화 체크 통과");
            workpath = saveDir;
        } else { // 미통과
            CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " FileProcess ERROR : 정규화 체크 미 통과"+saveDir);
             try {
                 task.taskCompErr(strMkDt, strMkSeq,"03", "point FileProcess ERROR : 정규화 체크 미 통과: "
                         +saveDir, strTitle+" Error",strTaskNo,strStrCd);
                 
             } catch (Exception e1) {
                 CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " error : " + ExceptionUtils.getFullStackTrace(e1));
             }
        }
        
        /* workpath 변경
         * workpath = saveDir;
            File f = new File(workpath);
         */
        
        File f = new File(XSSUtil.cleanPathMani(workpath),"");
        boolean b = false;
    
        CommonUtil.println(strTaskBatchCd, ">>>>>>>>>> FileProcess workpath : " + workpath);
        CommonUtil.println(strTaskBatchCd, ">>>>>>>>>> FileProcess strInsertQueryId : " + strInsertQueryId);
        CommonUtil.println(strTaskBatchCd, ">>>>>>>>>> FileProcess filterStr : " + filterStr);
        
        FilenameFilter filter = new FileFilter(filterStr);
        File[] arrF = f.listFiles(filter);                      
        Vector v = null;
        int pk = 0;
        String cmd = "";
        //conn.setAutoCommit(false);
    
        PreparedStatement insPstmt = null;
        StringBuffer insSb ;
    
        insSb = star.util.XmlParser.xmlReader(strXmlPath,strInsertQueryId);
        insPstmt = this.husDbCon.prepareStatement(insSb.toString());
        try{
                        
            if(arrF == null || arrF.length == 0){
                //SmsSend sms = new SmsSend();
                
                throw new CommonException("("+filterStr+") 수신된 파일이 없습니다.");
            }else {  
                if (strInsertQueryId.equals("INS_IF_CARD022F")){    //INF.CARD022F : 포인트시스템고객카드BIN
                    this.delete("DEL_IF_CARD022F" );  
                }else if (strInsertQueryId.equals("INS_IF_MSHP900F")){    //INF.MSHP900F : 고객정보수신 
                    this.delete("DEL_IF_MSHP900F" );                           
                }else if (strInsertQueryId.equals("INS_IF_MSHP910F")){    //INF.MSHP910F : 고객카드정보 
                    this.delete("DEL_IF_MSHP910F" );       
                }else if (strInsertQueryId.equals("INS_IF_POINT300F")){    //INF.POINT300F : 신세계포인트 적립 수신집계 
                    this.delete("DEL_IF_POINT300F" );  
                }else if (strInsertQueryId.equals("INS_IF_POINT310F")){    //INF.POINT310F : 신세계포인트 적립 상세 
                    this.delete("DEL_IF_POINT310F" );   
                }else if (strInsertQueryId.equals("INS_IF_POINT320F")){    //INF.POINT300F : 신세계포인트 사용 수신집계  
                    this.delete("DEL_IF_POINT320F" );   
                }else if (strInsertQueryId.equals("INS_IF_POINT330F")){    //INF.POINT310F : 신세계포인트 수신 상세   
                    this.delete("DEL_IF_POINT330F" );  
                } 
                for (int i = 0; i < arrF.length; i++) {
                    // 처리하지 않은 화일이면
    
                    //파일 사이즈 0이면 에러 처리
                    if (arrF[i].length() ==0){
                        throw new CommonException("("+filterStr+") 파일 크기가 0입니다.");
                    }
                    
                        v = fileRead(arrF[i]);
                        // 원본 등록처리
                        for (int j = 0; j < v.size(); j++) {
//                            System.out.print("-");
//                            if (j > 0 && j % 1000 == 0) {
                               // m_Logger.info("전체건수=" + v.size() + " 처리건수= " + j );         
//                            }

```
                        //process( (String) v.get(j));
                        selectAndInsert((String) v.get(j) ,strInsertQueryId,insPstmt );
                    }
                    // [2018-11-07] - start
                    //처리된 파일 백업
                    boolean rtn = arrF[i].renameTo(new File(backupPath+fileSeperator+arrF[i].getName()+"_"+getToday()))   ;
                    //arrF[i].delete();
                    // [2018-11-07] - end
            }
        }
        husDbCon.commit();
        
        if (insPstmt != null) {
            insPstmt.close();
            insPstmt = null;
        }
    }catch(Exception ex){
        husDbCon.rollback();
        throw new CommonException(CommonUtil.errorToString(ex));
    }finally{
        try{
            v.clear();
            v = null;
        }catch(Exception e) {
            CommonUtil.println(strTaskBatchCd, "FileProcess Error " + e);
        }
        if(husDbCon != null) husDbCon.close();
        
    }
    b = true;

    return b;
}
```