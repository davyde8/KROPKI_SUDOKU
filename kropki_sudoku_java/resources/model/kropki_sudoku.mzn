include "alldifferent.mzn";

int: n;

set of int: INDICES= 1..n;
set of int: DIGITS= 0..n;

enum TABLE_COLUMNS ={ROW1,COL1,ROW2,COL2,TYPE};

array[int,TABLE_COLUMNS] of int: points;

array [INDICES,INDICES] of DIGITS: input;

array[INDICES,INDICES] of var INDICES: out;

%check n input
constraint assert(n>1,"n must be greather than 1");

%check point input
constraint forall(k in index_set_1of2(points))(
  assert (points[k,ROW1]>=0 /\ points[k,ROW1]<=n /\
          points[k,COL1]>=0 /\ points[k,COL1]<=n /\
          points[k,ROW2]>=0 /\ points[k,ROW2]<=n /\
          points[k,COL2]>=0 /\ points[k,COL2]<=n,"Invalid point at row"++show_int(2,k))
);

%respect input matrix for assignment
constraint forall(i in INDICES, j in INDICES)(
if(input[i,j]!=0) then
out[i,j]=input[i,j]
endif
);

%check over points array
constraint forall(k in index_set_1of2(points) where points[k,TYPE]==0)(
(out[points[k,ROW2],points[k,COL2]]=out[points[k,ROW1],points[k,COL1]]*2) \/
(out[points[k,ROW1],points[k,COL1]]=out[points[k,ROW2],points[k,COL2]]*2));


%check over consecutive array
constraint forall(k in index_set_1of2(points) where points[k,TYPE]==1)(
(out[points[k,ROW2],points[k,COL2]]=out[points[k,ROW1],points[k,COL1]]+1) \/ (out[points[k,ROW1],points[k,COL1]]=out[points[k,ROW2],points[k,COL2]]+1));

%check all differente elements in each row and in each column
constraint forall(i in INDICES)(alldifferent([out[i,j] | j in INDICES]) /\ alldifferent([out[j,i] | j in INDICES]));

constraint forall(i in INDICES, j in 1..n-1)(
  (out[i,j]*2==out[i,j+1] \/ out[i,j]+1==out[i,j+1] \/ out[i,j]==out[i,j+1]*2 \/ out[i,j]==out[i,j+1]+1 )-> (exists(k in index_set_1of2(points))(
    points[k,ROW1]==i /\ points[k,ROW2]==i /\ points[k,COL1]==j /\ points[k,COL2]==j+1
  ))
);

constraint forall(i in 1..n-1, j in INDICES)(
  (out[i,j]*2==out[i+1,j] \/ out[i,j]+1==out[i+1,j] \/ out[i,j]==out[i+1,j]*2 \/ out[i,j]==out[i+1,j]+1)-> (exists(k in index_set_1of2(points))(
    points[k,ROW1]==i /\ points[k,ROW2]==i+1 /\ points[k,COL1]==j /\ points[k,COL2]==j
  ))
);

solve satisfy;

output[
show_int(2,out[i,j]) ++ if j==n then "\n" else " " endif | i in INDICES,j in INDICES
];
