import { Component, OnInit, ViewChild } from '@angular/core';
import { VaultService } from './vaultadmin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-vaultadmin',
  templateUrl: './vaultadmin.component.html',
  styleUrls: ['./vaultadmin.component.scss']
})

export class VaultadminComponent implements OnInit {
  constructor(private vaultservice: VaultService, public vaultAdminservice: VaultService) {
    this.show = false;
  }

  columnDefs: any = [{ headerName: 'VaultKeyName', field: 'name' }];
  gridApi: any;
  gridColumnApi: any;
  rowSelection: "single" | "multiple" | undefined = "single";
  selectedRows: any;
  defaultColDef = { editable: false, sortable: true, resizable: true, filter: true };
  paginationPageSize = 10;
  rowData: any[] = [];
  rowIndex: Number = 0;

  public internal: boolean | undefined = false;
  public external: Boolean | undefined = false;

  public displayModel: String = 'none';
  public Userdetails: any[] = [];
  public externalLink: any = null;
  private credentialsDeleteId: any = ''
  public show: boolean;
  createProject: FormGroup = new FormGroup({});

  public externalVault: any = {
    ipaddress: '',
    portnumber: '',
    username: '',
    password: '',
    version: ''
  }
  public storeExternal: any = {
    userId: '',
    credentials: [],
    role: ''
  }

  public internalPath: any;
  public internalPathObj: any;
  public listVault: any[] = [];

  data = [
    {
      name: 'Internal',
      image: 'https://img.freepik.com/premium-vector/shield-with-check-mark-safe-box-icon_24911-5022.jpg',
      description: 'Internal Local Vault',
      access: 'Edit Internal'
    },
    {
      name: 'External',
      image: 'https://img.freepik.com/premium-vector/shield-with-check-mark-safe-box-icon_24911-5022.jpg',
      description: 'External Vault'
    }
  ];

  visible = false;
  listvisible = false;
  loading = false;
  popupInternal = false;
  internalvalue = true;

  open(data: string): void {
    if (data == 'Internal') {
      this.internal = true;
      this.visible = true;
    } else if (data == 'External') {
      if (this.externalLink == null) {
        this.external = true;
        this.visible = true;
      } else {
        window.open(this.externalLink);
      }
    }
  }

  close(): void {
    this.internal = false;
    this.external = false;
    this.visible = false;
  }

  async ngOnInit() {
    this.getall();
    this.openList()
  }

  hideEye() {
    this.show = !this.show;
  }


  localvault() {
    // window.open('http://'+window.location.hostname+':8200');
  }

  DeleteData(): void {
    this.vaultAdminservice.StoreExternalVaultUrlDelete(this.credentialsDeleteId).subscribe((res: any) => {
      this.externalLink = null;
      this.externalVault = {
        ipaddress: '',
        portnumber: '',
        username: '',
        password: '',
        version: ''
      }
      this.ngOnInit();
    });
  }

  UpdateModel() {
    this.displayModel = 'block';
  }

  onGridReady(params: any) {
    this.gridApi = params.api;
    this.gridApi.sizeColumnsToFit();
    this.gridColumnApi = params.columnApi;
  }

  UpdateData() {
    let data: any = {
      _id: this.credentialsDeleteId,
      credentials: []
    }
    data.credentials.push(this.externalVault)
    this.vaultAdminservice.StoreExternalVaultUrlUpdate(data).subscribe((res: any) => {
      this.onCloseHandled();
      this.ngOnInit();
    });
  }

  //Open the Popup Model Box
  openModal() {
    if (this.externalLink === null) {
      this.displayModel = 'block';
    } else {
      window.open(this.externalLink);
    }
  }
  onCloseHandled() {
    this.displayModel = 'none';
  }

  storeExternalVault() {
    this.storeExternal.userId = sessionStorage.getItem('Id');
    this.storeExternal.credentials.push(this.externalVault);
    this.storeExternal.role = "Admin";
    this.vaultAdminservice.StoreExternalVaultUrlAdd(this.storeExternal).subscribe((res: any) => {
      this.storeExternal = {
        userId: '',
        credentials: [],
        role: ''
      }
      this.visible = false;
      this.onCloseHandled();
      this.getall();
    })
  }

  public async getall() {
    await this.vaultAdminservice.StoreExternalVaultUrlAll().subscribe((res) => {
      console.log(res)
      if (res.length == 0 && Array.isArray(res)) {
        this.externalLink = null;
        console.log('test')
      } else {
        console.log('test2')
        this.externalLink = res[0].credentials[0].ipaddress + ':' + res[0].credentials[0].portnumber;
        this.credentialsDeleteId = res[0]._id;
        this.externalVault = {
          ipaddress: res[0].credentials[0].ipaddress,
          portnumber: res[0].credentials[0].portnumber,
          username: res[0].credentials[0].username,
          password: res[0].credentials[0].password,
          version: res[0].credentials[0].version
        }
      }
    });
  }


  //internal vault 
  createVault() {
    this.vaultAdminservice.createInternVault(this.internalPath, JSON.parse(this.internalPathObj)).subscribe((response) => {
      console.log(response);
      this.internalPath = '';
      this.internalPathObj = '';
      this.popupInternal = false;
    })
  }

  openList() {
    this.listvisible = false;
    this.vaultAdminservice.getAllCredentials().subscribe((response) => {
      console.log(response);
      this.rowData = response.data.keys.map((name: any) => { return { name } });
      console.log(this.rowData);
    })
  }

  updatevault(objKey: any) {
    this.internalvalue = false;
    this.vaultAdminservice.getByVaultName(objKey).subscribe((response: any) => {
      this.internalPath = objKey;
      this.internalPathObj = JSON.stringify(response.data);
      this.open('Internal');
    })
  }

  updateByVaultName() {
    this.vaultAdminservice.updateData(this.internalPath, JSON.parse(this.internalPathObj)).subscribe((data) => {
      console.log(data);
      this.internalPath = '';
      this.internalPathObj = '';
      this.openList();
      this.internal = false;
    })
  }

  deleteByVaultId() {
    this.vaultAdminservice.deleteVaultData(this.internalPath).subscribe((response) => {
      this.internal = false;
      this.internalPath = '';
      this.internalPathObj = '';
      this.openList();
      this.internal = false;
    });
  }

  onSelectionChanged(event: any) {
    this.selectedRows = this.gridApi.getSelectedRows();
    this.updatevault(this.selectedRows[0].name);
  }

  createInternpopup() {
    this.popupInternal = true;
  }


  handleCancel(): void {
    this.popupInternal = false;
  }
}
