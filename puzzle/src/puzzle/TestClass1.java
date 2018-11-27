package puzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestClass1 {

	private static Long noOfNodes;
	private static double distance;
	private static Map<Integer,Node> nodes = new HashMap<Integer,Node>();
	private static Set<Integer> deleteNodes = new HashSet();
	//private static List<Node> repeatingNodes = new ArrayList();

	public static void main(String args[]) throws NumberFormatException,
			IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			String[] nodeAndDistance = reader.readLine().split(" ");
			noOfNodes = Long.parseLong(nodeAndDistance[0]);
			distance = Double.parseDouble(nodeAndDistance[1]);
		} catch (NumberFormatException | IOException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < noOfNodes; i++) {
			Node node = new Node();
			try {
				String[] xAndY = reader.readLine().split(" ");
				node.setX(Long.parseLong(xAndY[0]));
				node.setY(Long.parseLong(xAndY[1]));
				node.setRepeatCount((long) 0);
				node.setNodeNumber(i);
				nodes.put(i,node);
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		determineTowers();
	}

	public static void determineTowers() {

		Node n1 = nodes.get(0);
		Node n2 = n1;
		int j = 1;
		int i = 0;
		for (;i < nodes.size()-1 ;) {
			n1 = nodes.get(i);
			n2 = nodes.get(j);
			double dist = distance(n1, n2);
			if (dist == distance) {
				List<Integer> newNodes = n1.getDistantNodes();
				newNodes.add(n2.getNodeNumber());
				n1.setDistantNodes(newNodes);
				n1.setRepeatCount(n1.getRepeatCount() + 1);
				nodes.put(n1.getNodeNumber(), n1);
			}
			j++;
			if (j >= nodes.size()) {
				if(n1.getRepeatCount() == 0){
					nodes.remove(n1.getNodeNumber());
				}
				i++;
				j = i + 1;
					if (i == noOfNodes - 1) {
						nodes.remove(n1.getNodeNumber() + 1);
						break;
					}
			}
		}
		int towerCount = 0;
		int h = 0;
		for (; h <nodes.size(); h++) {
			Node node = nodes.get(h);
			if(node!=null && node.getRepeatCount() > 1){
				int k=0;
				for(;k<node.getDistantNodes().size();k++){
					int index = node.getDistantNodes().get(k);
					Long count = (long) 0;
//					if(deleteNodes.contains(index)){
//						continue;
//					}
					try{
					count = nodes.get(index).getRepeatCount();
					}
					catch(Exception e){
						continue;
					}
					if(count == 0){
						//nodes.remove(node.getDistantNodes().get(k));
						deleteNodes.add(node.getNodeNumber());
						towerCount++;
						break;
					}
					int newCount = (int) (node.getRepeatCount()-1);
					node.setRepeatCount((long) newCount);
					nodes.put(node.getNodeNumber(),node);
				}
			}
			
			if(node!=null && node.getRepeatCount() == 1){
				deleteNodes.add(node.getNodeNumber());
				towerCount++;
				continue;
			}
			
			if(node!=null && node.getRepeatCount() == 1){
			//	int nextIndex = node.getDistantNodes().get(0);
				Node next = null;
//				if(deleteNodes.contains(nextIndex))
//							continue;
				
				next = nodes.get(node.getDistantNodes().get(0));
				if(next!=null && next.getRepeatCount() == 0){
					//nodes.remove(next.getNodeNumber());
					deleteNodes.add(next.getNodeNumber());
					towerCount++;
					continue;
				}
				else if(next!=null && next.getRepeatCount() >= 1){
					//nodes.remove(next.getNodeNumber());
					deleteNodes.add(next.getNodeNumber());
					towerCount++;
					continue;
				}
				else if(next == null){
					//deleteNodes.add(next.getNodeNumber());
					towerCount++;
					continue;
				}
			}
		}

		System.out.println(deleteNodes.size());
		//System.out.println(h);
		//System.out.println(nodes.size());
		System.out.println("TowerCount:"+towerCount);
		return;
	}

	public static double distance(Node n1, Node n2) {
		Long x = n1.getX() - n2.getX();
		Long y = n1.getY() - n2.getY();
		double distance = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
		return distance;
	}
}

class Node {
	private int nodeNumber;
	private Long x;
	private Long y;
	private Long repeatCount;
	private List<Integer> distantNodes = new ArrayList<Integer>();
	private String status;

	public Long getX() {
		return x;
	}

	public void setX(Long x) {
		this.x = x;
	}

	public Long getY() {
		return y;
	}

	public void setY(Long y) {
		this.y = y;
	}

	public Long getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(Long repeatCount) {
		this.repeatCount = repeatCount;
	}

	public List<Integer> getDistantNodes() {
		return distantNodes;
	}

	public void setDistantNodes(List<Integer> distantNodes) {
		this.distantNodes = distantNodes;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
