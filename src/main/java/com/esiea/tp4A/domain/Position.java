package com.esiea.tp4A.domain;

public interface Position {
	 int getX();
	    int getY();
	    
	    static Position of(int x, int y) {
	        return new FixedPosition(x, y);
	    }
	    
	    final class FixedPosition implements Position {

	        private final int x;
	        private final int y;

	        public FixedPosition(int x, int y) {
	            this.x = x;
	            this.y = y;
	        }

	        @Override
	        public int getX() {
	            return x;
	        }

	        @Override
	        public int getY() {
	            return y;
	        }
	    }
}
