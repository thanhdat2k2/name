class CommonMenu extends HTMLElement {
  connectedCallback() {
    this.innerHTML = `<div class="menu-item p-3">
          <div class="d-flex align-items-center">
            <i class="fa-brands fa-dev me-2" style="font-size: 2rem"></i>
            <p class="fw-bold m-0">IMS</p>
          </div>
        </div>
        <div>
            <div class="nav-menu menu-item text-center p-3">
              <i class="fa-solid fa-house fs-3"></i>
            </div>
            <div class="nav-menu menu-item text-center p-3">
              <i class="fa-solid fa-users fs-3"></i>
              <p class="m-0">Candidate</p>
            </div>
            <div class="nav-menu menu-item text-center p-3">
              <i class="fa-solid fa-briefcase fs-3"></i>
              <p class="m-0">Job</p>
            </div>
            <div class="nav-menu menu-item text-center p-3">
              <i class="fa-regular fa-comments fs-3"></i>
              <p class="m-0">Interview</p>
            </div>
            <div class="nav-menu menu-item text-center p-3">
              <i class="fa-solid fa-file-lines fs-3"></i>
              <p class="m-0">Offer</p>
            </div>
            <div class="nav-menu menu-item text-center p-3">
              <i class="fa-solid fa-user-pen fs-3"></i>
              <p class="m-0">User</p>
            </div>
        </div>`;
  }
}

class CommonHeader extends HTMLElement {
  connectedCallback() {
    this.innerHTML = `<div class="row">
            <div class="d-flex justify-content-end align-items-end pe-5">
                <div class="account m-2 text-end">
                    <p class="m-0"><b>hoannk</b></p>
                    <p class="m-0">HR Department</p>
                </div>
                <div class="icon m-2">
                    <i class="fa-regular fa-user"></i>
                </div>
                <div class="logout m-2">
                    <a href="">Logout</a>
                </div>
            </div>
        </div>`;
  }
}


customElements.define("common-menu", CommonMenu);
customElements.define("common-header", CommonHeader);