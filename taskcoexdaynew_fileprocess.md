 public boolean FileProcess(String strInsertQueryId) throws Exception {       

        UpdateTask task = null;
        String regEx = "^/home/INF/coexDay/new/$";
        String regEx2 = "^/home/INF/coexDay/new/bak/$";
        
        try {
            task = new UpdateTask();
        } catch (Exception e) {
            CommonUtil.println(strTaskBatchCd, "TaskCoexDayNew FileProcess UpdateTask error " + ExceptionUtils.getFullStackTrace(e));
        }        
      
        if (XSSUtil.chkRegExp(regEx, saveDir)) {
            CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " FileProcess 정규화 체크 통과");
            workpath = saveDir;
        } else {
            CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " FileProcess ERROR : 정규화 체크 미 통과"+saveDir);
             try {
                 task.taskCompErr(strMkDt, strMkSeq,"03", "TaskCoexDayNew FileProcess ERROR : 정규화 체크 미 통과: "+saveDir, strTitle+" Error",strTaskNo,strStrCd);
                 
             } catch (Exception e1) {
                 CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " error : " + ExceptionUtils.getFullStackTrace(e1));
             }
        }
    
        File f = new File(XSSUtil.cleanPathMani(workpath),"");
        
        boolean b = false;
        boolean chkErr = true; 
        
        filterStr = fileName; //inf000에서 d로설정해줌. 344line

//        System.out.println("filterStr : " + filterStr);
        FilenameFilter filter = new FileFilter(filterStr);
        File[] arrF = f.listFiles(filter);
    
        Vector v = null;

//        for (int i = 0; i < arrF.length; i++) {
//            System.out.println("arrF[i].getName() : "+arrF[i].getName());
//        }
​        
//        if(1==1) return true;
​        
        //conn.setAutoCommit(false);

        PreparedStatement delPstmt1 = null;
        PreparedStatement delPstmt2 = null;
        PreparedStatement insPstmt1 = null;
        PreparedStatement insPstmt2 = null;
    
        StringBuffer delSb1 ;
        StringBuffer delSb2 ;
        StringBuffer insSb1 ;
        StringBuffer insSb2 ;        

//        selSb  = star.util.XmlParser.xmlReader(strXmlPath,"SEL_IF_EVALU210F");
        delSb1 = star.util.XmlParser.xmlReader(strXmlPath,"DEL_IF_EVALU200F");
        delSb2 = star.util.XmlParser.xmlReader(strXmlPath,"DEL_IF_EVALU210F");
        insSb1 = star.util.XmlParser.xmlReader(strXmlPath,"INS_IF_EVALU200F");
        insSb2 = star.util.XmlParser.xmlReader(strXmlPath,"INS_IF_EVALU210F");

//        selPstmt  = this.husDbCon.prepareStatement(selSb.toString());
        delPstmt1 = this.husDbCon.prepareStatement(delSb1.toString());
        delPstmt2 = this.husDbCon.prepareStatement(delSb2.toString());
        insPstmt1 = this.husDbCon.prepareStatement(insSb1.toString());
        insPstmt2 = this.husDbCon.prepareStatement(insSb2.toString());
        
        //inf.EVALU200F삭제
        delPstmt1.executeUpdate();
        //inf.EVALU210F삭제
        delPstmt2.executeUpdate();
                
        try{
            if(arrF == null || arrF.length == 0){
                //SmsSend sms = new SmsSend();
                throw new CommonException("FTP파일없음("+filterStr+") 수신된 파일이 없습니다.");
            }else { 
                for (int i = 0; i < arrF.length; i++) {
                    //에러체크
                    chkErr = true;
                    // 처리하지 않은 화일이면
    
                    //파일 사이즈 0이면 에러 처리
//                    if (arrF[i].length() ==0){
//                        throw new CommonException("("+filterStr+") 파일 크기가 0입니다.");                        
//                    }
                        strFileNm = arrF[i].getName();
//                        System.out.println("strFileNm : "+ strFileNm);
​                    
                        v = fileRead(arrF[i]);
                        // 원본 등록처리
//                        if (v.size()<3){
//                            //throw new CommonException("("+filterStr+") 헤더만 존재합니다. 데이타가 없습니다.");
//                            task.taskCompLog(strMkDt, strMkSeq,"("+filterStr+") 헤더만 존재합니다. 데이타가 없습니다.");
//                            m_Logger.info("("+filterStr+") 헤더만 존재합니다. 데이타가 없습니다.");
//                            arrF[i].renameTo(new File(backupPath+fileSeperator+arrF[i].getName()+"_"+getToday()))   ;          
//                            return true;
//                        }
                        for (int j = 0; j < v.size(); j++) {
//                            if (j > 0 && j % 1000 == 0) {
//                                m_Logger.info("전체건수=" + v.size() + " 처리건수= " + j );         
//                            }
                            //process( (String) v.get(j));
                            try{
                                selectAndInsert((String) v.get(j) ,strInsertQueryId ,insPstmt1,insPstmt2);
                            }catch(Exception e){
                                husDbCon.rollback();   
                                chkErr = false;
                                CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " error " +"수신된 FTP파일("+strFileNm+")에 에러가 있습니다."+ CommonUtil.errorToString(e));
                                task.taskCompLog(strMkDt, strMkSeq,"수신된 FTP파일("+strFileNm+")에 에러가 있습니다.");
    
                            }
                        }
                        husDbCon.commit();     
                       
                        if (XSSUtil.chkRegExp(regEx2, backupPath)) { //백업폴더 정규식체크 
                            CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " FileProcess backupPath 정규화 체크 통과");
                        //처리된 파일 백업
                            if (chkErr){
                                    arrF[i].renameTo(new File(backupPath+fileSeperator+arrF[i].getName()+"_"+getToday()))   ;
                       
                            }else{
                                cntErr+=1;
                                arrF[i].renameTo(new File(backupPath+fileSeperator+arrF[i].getName()+"_"+getToday()+"_Error"))   ;
                            }
                        }else{
                                CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " FileProcess backupPath ERROR : 정규화 체크 미 통과"+backupPath);
                                try {
                                    task.taskCompErr(strMkDt, strMkSeq,"03", "TaskCoexDayNew FileProcess backupPath ERROR : 정규화 체크 미 통과: "+backupPath, strTitle+" Error",strTaskNo,strStrCd);
                                    
                                } catch (Exception e1) {
                                    CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " error : " + ExceptionUtils.getFullStackTrace(e1));
                                }
                            }
                        
                        }
                }
//            husDbCon.commit();
​            
            try{
            if (delPstmt1 != null) {
                delPstmt1.close();
                delPstmt1 = null;
            }
            if (delPstmt2 != null) {
                delPstmt2.close();
                delPstmt2 = null;
            }
            if (insPstmt1 != null) {
                insPstmt1.close();
                insPstmt1 = null;
            }
            if (insPstmt2 != null) {
                insPstmt2.close();
                insPstmt2 = null;
            }
            }catch(Exception e){
//                e.printStackTrace();
                CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " error " + CommonUtil.errorToString(e));
            }
        }catch(Exception ex){
//            System.out.println(ex);
            CommonUtil.println(strTaskBatchCd, strTaskBatchCd + " error " + CommonUtil.errorToString(ex));
            //throw new CommonException(ex.getMessage());
        }finally{
            if(husDbCon != null) husDbCon.close();
        }
        b = true;
    
        return b;
    }