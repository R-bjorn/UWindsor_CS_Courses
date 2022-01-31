function A = powerfit(X ,Y, M)
  sumxmy = ((X).^M)*(Y)';
  n = 2*M;
  sumx2m = sum(X.^n)';
  A=sumxmy/sumx2m;