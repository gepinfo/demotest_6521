import { AdminController } from '../controllers/admincontroller';

let admincontroller = new AdminController();
export class Routes {

    private callConstructor:string;
    constructor() { 
        this.callConstructor = "callConstructor";
    }

    public routes(app): void {
        app.route('/admin/getusers').get(admincontroller.adminuser);
        app.route('/admin/getuser/:id').get(admincontroller.getuser);
        app.route('/admin/getallroles').get(admincontroller.getroles);
        app.route('/admin/updateuser').put(admincontroller.updateuser);
    }
}