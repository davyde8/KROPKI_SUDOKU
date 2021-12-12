include "alldifferent.mzn";

int: n;

set of int: INDICES= 1..n;
set of int: DIGITS= 0..n;

array[INDICES,INDICES] of var INDICES: out;
array [INDICES,INDICES] of DIGITS: input;


%respect input matrix for assignment
constraint forall(i in INDICES, j in INDICES)(
if(input[i,j]!=0) then
out[i,j]=input[i,j]
endif
);

%check all different elements in each row and in each column
constraint forall(i in INDICES)(alldifferent([out[i,j] | j in INDICES]) /\ alldifferent([out[j,i] | j in INDICES]));

solve satisfy;

output[
show_int(2,out[i,j]) ++ if j==n then "\n" else "," endif | i in INDICES,j in INDICES
];
