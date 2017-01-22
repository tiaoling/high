namespace java com.xiaojiuwo.example  // defines the namespace    
     
typedef i32 int  //typedefs to get convenient names for your types   
     
service ThriftTestService {    
    string test(1:string name),//delay 3s  
}  