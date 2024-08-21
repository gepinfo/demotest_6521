import * as express from 'express';
import * as dotenv from "dotenv";
dotenv.config();
import * as bodyParser from 'body-parser';
import { Routes } from './routes/routes';
import * as mongoose from 'mongoose';
import * as cors from 'cors';
import { WinstonLogger } from './config/WinstonLogger';
import { SCMService } from './apiservices/systemcredentialsmanager';

const PORT = 3015;

class App {

    public app: express.Application = express();
    public routePrv: Routes = new Routes();
    public logger: WinstonLogger = new WinstonLogger();
    public DB_Url: string;
    public DB_Domain: string = process.env.MONGO_DOMAIN;

    constructor() { 
        this.DatabaseCredentials();
        this.logger.setupLogger();
        this.logger.configureWinston(this.app);
        this.initializeMiddlewares();
        this.routePrv.routes(this.app);
        dotenv.config();
    }

    private initializeMiddlewares() {
        this.app.use(bodyParser.json());
        this.app.use(bodyParser.urlencoded({ extended: false }));
        this.app.use('/static', express.static('public'))
        this.app.use(cors({ credentials: true, origin: true }))
        this.app.use(function(req, res, next) {
            res.header("Access-Control-Allow-Origin", "Origin");
            res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
           next();
          });
    }

    private DatabaseCredentials() {
        let SCMconfig = new SCMService();
        SCMconfig.SCMData( async result => {
            this.DB_Url = result.data.DB_URL_demotest_6521;
            let db_auth = result.data.DB_AUTH_demotest_6521;
            let db_coll = result.data.DB_COLL_demotest_6521;
            this.DB_Url = db_auth+this.DB_Domain+db_coll;
            this.DBSetup();
        });
    }

    private DBSetup(): void {
        console.log("db url",this.DB_Url);
        (<any>mongoose).Promise = global.Promise;
        mongoose.connect(this.DB_Url, { useNewUrlParser: true});
    }

}

new App().app.listen(PORT, () => {
    console.log('Express server listening on port  ' + PORT);
})