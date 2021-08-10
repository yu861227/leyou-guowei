<template>
  <v-card>
<!--    布局-->
      <v-layout row>
        <!-- 商品分类 -->
        <v-flex xs3>
          <v-card>
            <v-card-title>选择分类，查看规格参数模板：</v-card-title>
            <v-divider/>
<!--点击事件 判断是否是父节点        -->
              <v-tree url="/item/category/list"
                  :isEdit="false"
                  @handleClick="handleClick"
          />
          </v-card>
        </v-flex>
        <!-- 规格参数 -->
        <v-flex xs9 class="px-1">
          <v-card class="elevation-0" v-show="currentNode.id">
              <v-card-title v-if="!currentNode.path">
                请先选择一个分类
              </v-card-title>
            <v-breadcrumbs dense v-else>
                <v-icon slot="divider">chevron_right</v-icon>
              <!--                  items 计算属性-->
                <v-breadcrumbs-item
                    v-for="(item,i) in items"
                    :key="i"
                >
                    <span v-if="i === 2" @click="back">{{ item }}</span>
                    <span v-else>{{ item }}</span>
                </v-breadcrumbs-item>
            </v-breadcrumbs>
            <v-divider/>
<!--            两个相反  分组信息  参数信息-->
            <v-card-text>
              <spec-group :cid="currentNode.id" v-show="showGroup" @select="selectGroup"/>
              <spec-param :group="group" v-show="!showGroup" />
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
  </v-card>
</template>

<script>

export default {
  name: "v-spec",
  data() {
    return {
      currentNode: {}, // 当前被选中的商品分类节点
      group:{}, // 被选中的分组
      showGroup:true, // 是否展示分组
    };
  },
  methods: {
    // 分类点击后的弹窗
    handleClick(node) {
      // 判断点击的节点是否是父节点（只有点击到叶子节点才会展示规格）
      if (!node.isParent) {
        // 把当前点击的节点记录下来
        this.currentNode = node;
        // 显示规格组
        this.showGroup = true;
        // 记录选中的规格组，默认为空
        this.group = {};
      }
    },
    selectGroup(group){
      // 记录选中的分组
      this.group = group;
      // 不再显示分组，而是显示规格参数
      this.showGroup = false;
    },
    back(){
        this.showGroup = true;
        this.group = {};
    }
  },
  components:{
    // 导入子组件
      SpecGroup: () => import('./SpecGroup.vue'),
      SpecParam: () => import('./SpecParam.vue'),
  },
  computed:{
      items(){
          const items = this.currentNode.path;
          if(this.group.name){
              items[3] = this.group.name;
          }
          return items;
      }
  }
};
</script>

<style scoped>
</style>
