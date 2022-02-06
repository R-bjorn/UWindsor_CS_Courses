function X = LU_trivedi(A,B)
  [~, N]=size(A);
  X=zeros(N,1);
  C=zeros(1,N);
  [L, ~, P]=lu(A);
  B = P*B;
  
  for i=1:N-1
      [~, j]=max(abs(A(i:N,i)));
      %Row interchange for A
      C=A(i,:);
      A(i,:)=A(j+i-1,:);
      A(j+i-1,:)=C;
      
      while A(i,i)==0
          if(i==N)
              'Unique solution not possible';
              break
          end
          
          C=A(i,:);
          A(i,:)=A(i+1,:);
          A(i+1,:)=C;
      end
          
      for k=i+1:N
          m=A(k,i)/A(i,i);
          A(k,i:N)=A(k,i:N)-m*A(i,i:N);
      end
  end
  Y=forwardsub(L,B);
  X=backsub(A,Y);
 
  