function D=qr2(A,epsilon)
  [n,n]=size(A);
  m=n;
  D=zeros(n,1);
  B=A;
  while(m>1)
      while(abs(B(m,m-1))>=epsilon)
          S=eig(B(m-1:m,m-1:m));
          [j,k]=min([abs(B(m,m)*[1 1]'-S)]);
          [Q,U]=qr(B-S(k)*eye(m));
          B=U*Q+S(k)*eye(m);
      end
      A(1:m,1:m)=B;
      m=m-1;
      B=A(1:m,1:m);
  end
  D=diag(A)