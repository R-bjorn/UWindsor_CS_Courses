function f = eq1(P)
  x = P(1);
  y = P(2);
  f=zeros(1,2);
  f(1) = (3 - 2*y)/2;
  f(2) = (4 - 3*(x.^2))/2;