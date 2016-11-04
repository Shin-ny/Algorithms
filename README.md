# Algorithms
Notes and homework from the book Algorithms (4th edition)

## CHAPTER 1

### 1.3 Bags, Queues, and Stacks
* Queues: FIFO (`Quenes.java` & `QuenesArr.java`)
* Stacks: LIFO (`Stacks.java` & `StacksArr.java`)
* Bags: ...


#### Aplication:
Using stacks to implement a simple calculator: `DijkstraTwoStackAlgorithm.java`

### 1.4 Analysis of Algorithms
Example: `ThreeSum.java`

#### Running time

Scientific method:

* Observe some feature of the natural world.
* Hypothesize a model that is consistent with the observations.
* Predict events using the hypothesis.
* Verify the predictions by making further observations.
* Validate by repeating until the hypothesis and observations agree.

Principles:

* Experiments must be reproducible.
* Hypotheses must be falsifiable.

#### Running memory
...

#### Common order-of-growth classifications
1, logN, N, N*log N, N^2, N^3, and 2^N

#### Types of analyses
* Best case. Lower bound on cost.
* Worst case. Upper bound on cost.
* Average case. “Expected” cost.

#### Theory of algorithms
Goals.
* Establish “difficulty” of a problem.
* Develop “optimal” algorithms.

Approach.
* Suppress details in analysis: analyze “to within a constant factor”.
* Eliminate variability in input model by focusing on the worst case.

Optimal algorithm.
* Performance guarantee (to within a constant factor) for any input.
* No algorithm can provide a better performance guarantee.

### 1.5 Union-find
#### Dynamic connectivity
...

#### Quick find
`Quick_Find.java`

#### Quick union
`Quick_Union.java` & `Quick_Union_Improv.java`

#### Application: Percolation
`Percolation.java`


