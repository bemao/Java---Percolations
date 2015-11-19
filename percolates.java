import QuickFind.QuickFind;

public class percolation {
	private int[][] grid;
	
	private QuickFind QF;
	
	public percolation(int N){
		if (N <= 0){
			throw new java.lang.IndexOutOfBoundsException();
		}
	    grid = new int[N][N];   //Stores information about which nodes are open. Initially all are closed
	 
	    //Initialize QuickFind array. Entries in the array are between 1 and N*N
	    //All entries in the first row will be mapped to 0
	    //All entries in the last row will be mapped to N*N+1
	    QF = new QuickFind(N*N+2);
	    for (int i = 1; i<N; i++) {
	    	QF.union(i, 0);
	    	QF.union(N*N+1-i, N*N+1);
	    }
	}
	
	public void open(int i, int j){
		
		grid[i-1][j-1] = 1;
		
		int N = grid.length;
		
		//unions top row elements with 0
		if (i == 1)
			QF.union(j, 0);
		else {
			if (isOpen(i-1,j))
				QF.union((i-1)*N+j, (i-2)*N+j);
			}
		//unions bottom row elements with N*N+1
		if (i == N)
			QF.union(N*(N-1)+j, N*N+1);
		else {
			if (isOpen(i+1,j))
				QF.union((i-1)*N+j, (i)*N+j);
			}
		if (j != N && isOpen(i,j+1))
				QF.union((i-1)*N+j, (i-1)*N+j+1);
		if (j != 1 && isOpen(i,j-1))
				QF.union((i-1)*N+j, (i-1)*N+j-1);	
		}
		
	
	public boolean isOpen(int i, int j){
		return grid[i-1][j-1] > 0;
	}
	
	public boolean isFull(int i, int j){
        if (!isOpen(i, j)) return false;
        
        int N = grid.length;
        
        return QF.isConnected((i-1)*N+j, 0);
	}
	
	public boolean percolates(){
		int N = grid.length;
		
		return QF.isConnected(0, N*N+1);
	}
