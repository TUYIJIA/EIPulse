<script>
import axios, { Axios } from 'axios';
import Page from "../../../components/Page.vue";
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      depts: [],
      currentSearch: {},
      DEPTName: '',
      totalPages: 0,
      currentPage: 1,
    }
  },
  components: {
    Page
  }, methods: {
    async loadDept() {
      let url = `http://localhost:8090/eipulse/dept/paged/${this.currentPage}`;

      if (this.DEPTName !== null && this.DEPTName !== '') {
        url = `http://localhost:8090/eipulse/dept/paged/${this.DEPTName}/${this.currentPage}`
      }

      try {
        const res = await axios.get(url);
        console.log(res.data.content);
        this.depts = res.data.content;
        console.log(this.depts);
        this.totalPages = res.data.totalPages;
      } catch (e) {
        console.log(e);
      }
    },
    updateCurrentPage(newPage) {
      console.log("測試測試");
      console.log(this.currentPage);

      this.currentPage = newPage;
      // 這裡加載新頁面的數據
      console.log(this.currentSearch.value);
      this.loadDept();
    },


    addDept() {
      this.$router.push(`/employee/add-dept`);
    },

    updateDept(deptId) {
      this.$router.push(`/employee/updateDept/${deptId}`);
      console.log(deptId);
    },


    async deleteDept(deptId) {
      console.log(deptId);
      if (confirm("確定要刪除嗎?")) {
        const url = `http://localhost:8090/eipulse/dept/delete/${deptId}`
        const data = await axios.delete(url)
        console.log("1545454");
        console.log(data);
        if (data.status == 200) {
          // 使用 sweetalert 成功提示
          Swal.fire({
            icon: 'success',
            title: '已刪除',
            text: data.message
          })
          // 刪除成功後
          this.loadDept();
        }
      }
    }
  },
  mounted() {
    this.loadDept();
  }
};
</script>

<template>
  <div class="card mx-2 p-2 mb-2">
    <div class="card-header text-center text-bg-dark fs-5">部門列表</div>
    <div class="card-body">
      <div class="mb-3" style="text-align:left; position:left;">
        <button type="button" class="btn btn-outline-success w-40 mb-2" @click="addDept">新增部門</button>
        <form @submit.prevent="loadDept" style="text-align: left">
          <div class="input-group">
            <input type="text" class="form-control custom-width" placeholder="輸入部門名稱" aria-label="輸入部門名稱"
              aria-describedby="button-addon2" v-model="DEPTName">
            <!-- <button class="btn btn-outline-secondary" type="button" id="button-addon2">查詢</button> -->
          </div>
        </form>
      </div>
      <table class="table table-bordered text-center">
        <thead>
          <tr style="background-color: rgb(255, 204, 0);; border-color: black;">
            <th>部門編號</th>
            <th>部門名稱</th>
            <th>部門辦公室</th>
            <th>編輯</th>
          </tr>
        </thead>
        <tbody style="background-color: rgb(255, 255, 255);border-color: rgb(0, 0, 0);;">
          <tr v-for="dept in depts" :key="dept.id">
            <td>{{ dept.deptId }}</td>
            <td>{{ dept.deptName }}</td>
            <td>{{ dept.deptOffice }}</td>
            <td>
              <button class="btn btn-secondary mx-1"><i class="bi bi-pencil-square" @click="updateDept(dept.deptId)"></i>
              </button>
              <button class="btn btn-secondary mx-1"><i class="bi bi-bucket" @click="deleteDept(dept.deptId)"></i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <Page :total-pages="totalPages" :current-page="currentPage" @select-page="updateCurrentPage"></Page>
    </div>
    <div class="card-footer text-body-secondary">
      Copyright © EIPulse科技 All Rights Reserved.
    </div>
    <window-modal class="modal-xl" titleName="產品編輯" ref="editModal">
      <form>
        <edit-input></edit-input>
      </form>
    </window-modal>
  </div>
</template>


<style scoped>
.card-body {
  max-height: 500px;
  overflow-y: auto;
  /* overflow-x: auto; */
}
</style>