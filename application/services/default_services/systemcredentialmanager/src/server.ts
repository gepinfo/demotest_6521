import * as express from 'express';
import * as bodyParser from 'body-parser';
import * as cors from 'cors';
import { WinstonLogger } from './config/Winstonlogger';
import { Routes } from './routes/routes'
import mongoose = require('mongoose');

import { VaultConfig } from './config/VaultConfig';
import { SeedService } from './seed';




const PORT = 8005;

class App {
    public app = express();
    public routerPrv: Routes = new Routes();
    public logger: WinstonLogger = new WinstonLogger();
    public DB_Url: string;
    public DB_Domain: any = process.env.MONGO_DOMAIN;
    

    constructor() {
        this.SeedData();
        this.config();
        this.routerPrv.routes(this.app);
        
        
       
       }

    private config(): void {
        this.app.use(bodyParser.json());
        this.app.use(bodyParser.urlencoded({ extended: false }));
        this.app.use(express.static("public"));
        this.app.use(cors({ credentials: true, origin: true }));
        
    }

    private DBSetup(): void {
        console.log('Database', this.DB_Url);
        mongoose.Promise = global.Promise;
        /** if your using the previous version mongo <=5  use this object { useNewUrlParser: true, useUnifiedTopology: true } */
        mongoose.connect(this.DB_Url).then( async data => {
            
        });
    }




    private DatabaseCredits() {
        let vaultconfig = new VaultConfig();
        vaultconfig.vaultConfig( async data => {
            let db_auth = data.DB_AUTH_demotest_6521;
            let db_coll = data.DB_COLL_demotest_6521;
            this.DB_Url = db_auth+this.DB_Domain+db_coll;
            this.DBSetup();
        });
    }
    public vault_Url = process.env.VAULT_URL;
    private async SeedData(): Promise<void> {
        let seedservice = new SeedService();
        await seedservice.initkvdata(this.vault_Url, async (callback) => {
            console.log(callback);
            this.DatabaseCredits();
        })
    }








}

new App().app.listen(PORT, () => {
    console.log('Express server listening on port  ' + PORT);
})