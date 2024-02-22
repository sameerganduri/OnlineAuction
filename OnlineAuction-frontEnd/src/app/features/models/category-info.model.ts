export class CategoryInfo {
    categoryId!: number;
    categoryName!: string;
    categoryDescription!: string;
    parentCategoryId!: number;
    subCategories!: CategoryInfo[];
  }
  