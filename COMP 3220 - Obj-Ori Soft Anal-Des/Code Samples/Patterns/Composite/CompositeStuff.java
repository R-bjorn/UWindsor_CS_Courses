package composite;

public class CompositeStuff {

	public static void main(String[] args) {
		Composite rwheel = new Composite("Right Wheel");

		Nut rnut1 = new Nut(5.0F);
		rwheel.addComponent(rnut1);
		
		Nut rnut2 = new Nut(5.0F);
		rwheel.addComponent(rnut2);
		
		Nut rnut3 = new Nut(5.0F);
		rwheel.addComponent(rnut3);
		
		Nut rnut4 = new Nut(5.0F);
		rwheel.addComponent(rnut4);
		
		Nut rnut5 = new Nut(7.0F);
		rwheel.addComponent(rnut5);
		
		WheelFrame rwf = new WheelFrame(26F);
		rwheel.addComponent(rwf);
		
		HubCap rhc = new HubCap(30F);
		rwheel.addComponent(rhc);

		System.out.println(rwheel);
		System.out.println(rwheel.getCost());
		

		Composite lwheel = new Composite("Left Wheel");
		
		Nut lnut1 = new Nut(6.0F);
		lwheel.addComponent(lnut1);
		
		Nut lnut2 = new Nut(6.0F);
		lwheel.addComponent(lnut2);
		
		Nut lnut3 = new Nut(6.0F);
		lwheel.addComponent(lnut3);
		
		Nut lnut4 = new Nut(6.0F);
		lwheel.addComponent(lnut4);
		
		Nut lnut5 = new Nut(7.0F);
		lwheel.addComponent(lnut5);
		
		WheelFrame lwf = new WheelFrame(28F);
		lwheel.addComponent(lwf);
		
		HubCap lhc = new HubCap(30F);
		lwheel.addComponent(lhc);

		System.out.println(lwheel);
		System.out.println(lwheel.getCost());
	
		Composite frontWheels = new Composite("Front Wheels");
		frontWheels.addComponent(lwheel);
		frontWheels.addComponent(rwheel);
		frontWheels.addComponent(new Axle(120F));

		System.out.println(frontWheels);
		System.out.println(frontWheels.getCost());
	}
}
